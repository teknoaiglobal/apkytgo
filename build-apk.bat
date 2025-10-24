@echo off
echo ========================================
echo    ANDROID BROWSER APK - AUTO BUILD
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java tidak ditemukan!
    echo [INFO] Download Java JDK 17: https://adoptium.net/
    echo [INFO] Set JAVA_HOME environment variable
    pause
    exit /b 1
)

echo [OK] Java ditemukan
java -version

REM Check if Android SDK exists
if not exist "%ANDROID_HOME%" (
    if not exist "%LOCALAPPDATA%\Android\Sdk" (
        echo [ERROR] Android SDK tidak ditemukan!
        echo [INFO] Install Android Studio: https://developer.android.com/studio
        echo [INFO] Atau set ANDROID_HOME environment variable
        pause
        exit /b 1
    ) else (
        set ANDROID_HOME=%LOCALAPPDATA%\Android\Sdk
        echo [OK] Android SDK ditemukan di: %ANDROID_HOME%
    )
) else (
    echo [OK] Android SDK ditemukan di: %ANDROID_HOME%
)

REM Add Android tools to PATH
set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools;%ANDROID_HOME%\build-tools\34.0.0

echo.
echo ========================================
echo         BUILDING DEBUG APK
echo ========================================
echo.

REM Clean previous builds
echo [1/4] Cleaning previous builds...
call gradlew.bat clean
if %errorlevel% neq 0 (
    echo [ERROR] Gradle clean failed!
    pause
    exit /b 1
)

echo [2/4] Syncing dependencies...
call gradlew.bat build --dry-run
if %errorlevel% neq 0 (
    echo [ERROR] Gradle sync failed!
    pause
    exit /b 1
)

echo [3/4] Building debug APK...
call gradlew.bat assembleDebug
if %errorlevel% neq 0 (
    echo [ERROR] APK build failed!
    echo [INFO] Check error messages above
    pause
    exit /b 1
)

echo [4/4] Verifying APK...
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo.
    echo ========================================
    echo           BUILD SUCCESS!
    echo ========================================
    echo.
    echo [SUCCESS] APK berhasil dibuat!
    echo [LOCATION] app\build\outputs\apk\debug\app-debug.apk
    echo.
    
    REM Get APK size
    for %%A in ("app\build\outputs\apk\debug\app-debug.apk") do (
        set size=%%~zA
        set /a sizeKB=!size!/1024
        echo [SIZE] !sizeKB! KB
    )
    
    echo.
    echo [NEXT STEPS]
    echo 1. Copy APK ke Android device
    echo 2. Enable "Install from Unknown Sources"
    echo 3. Install APK dan test aplikasi
    echo.
    
    REM Ask if user wants to install to connected device
    adb devices >nul 2>&1
    if %errorlevel% equ 0 (
        echo [OPTION] Install ke device yang terhubung? (y/n)
        set /p install="Pilihan: "
        if /i "!install!"=="y" (
            echo [INSTALLING] Installing APK to device...
            adb install -r "app\build\outputs\apk\debug\app-debug.apk"
            if %errorlevel% equ 0 (
                echo [SUCCESS] APK berhasil diinstall ke device!
            ) else (
                echo [ERROR] Install gagal. Check device connection.
            )
        )
    )
    
) else (
    echo [ERROR] APK file tidak ditemukan!
    echo [INFO] Check build logs untuk error details
)

echo.
echo ========================================
echo              SELESAI
echo ========================================
pause