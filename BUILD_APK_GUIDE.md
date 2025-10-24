# 📱 Panduan Lengkap Build APK - Browser Android

## 🎯 Cara Membuat APK Siap Install

### **Metode 1: Menggunakan Android Studio (Recommended)**

#### **Step 1: Persiapan Environment**
1. **Download & Install Java JDK 17+**
   ```
   https://adoptium.net/temurin/releases/
   ```
   - Pilih JDK 17 LTS
   - Install dan set JAVA_HOME

2. **Download & Install Android Studio**
   ```
   https://developer.android.com/studio
   ```
   - Install dengan semua komponen default
   - Tunggu download SDK selesai

#### **Step 2: Import Project**
1. Buka Android Studio
2. **File → Open**
3. Pilih folder `AndroidBrowserApp`
4. Klik **OK**
5. Tunggu **Gradle Sync** selesai (5-10 menit pertama kali)

#### **Step 3: Build APK Debug**
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```
- APK akan tersimpan di: `app/build/outputs/apk/debug/app-debug.apk`
- File size: ~2-5 MB
- Siap install di device Android

#### **Step 4: Build APK Release (Production)**
```
Build → Generate Signed Bundle / APK
```
1. Pilih **APK** → Next
2. **Create new keystore**:
   - Key store path: `C:\AndroidKeys\browser-app.jks`
   - Password: `browser123` (atau password kuat)
   - Key alias: `browser-key`
   - Key password: `browser123`
   - Validity: 25 years
3. Pilih **release** build variant
4. **V1 & V2 Signature** → Finish
5. APK release: `app/build/outputs/apk/release/app-release.apk`

### **Metode 2: Command Line Build**

#### **Prerequisites**
```bash
# Set JAVA_HOME
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.8.101-hotspot

# Set ANDROID_HOME
set ANDROID_HOME=C:\Users\%USERNAME%\AppData\Local\Android\Sdk

# Add to PATH
set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools
```

#### **Build Commands**
```bash
# Masuk ke project directory
cd AndroidBrowserApp

# Build debug APK
gradlew.bat assembleDebug

# Build release APK (perlu keystore)
gradlew.bat assembleRelease

# Install langsung ke device
gradlew.bat installDebug
```

### **Metode 3: Online Build Service**

#### **GitHub Actions (Free)**
1. Upload project ke GitHub
2. Buat file `.github/workflows/build.yml`:

```yaml
name: Build APK
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
    - name: Build APK
      run: |
        cd AndroidBrowserApp
        chmod +x gradlew
        ./gradlew assembleDebug
    - name: Upload APK
      uses: actions/upload-artifact@v3
      with:
        name: app-debug
        path: AndroidBrowserApp/app/build/outputs/apk/debug/app-debug.apk
```

#### **AppCenter (Microsoft)**
```
https://appcenter.ms/
```
- Free build service
- Upload source code
- Automatic APK generation

## 📋 APK Information

### **Debug APK Specs**
- **File**: `app-debug.apk`
- **Size**: ~3-5 MB
- **Min Android**: 5.0 (API 21)
- **Target Android**: 14 (API 34)
- **Permissions**: Internet, Network State
- **Features**: Custom Chrome Tabs, WebView Fallback

### **Release APK Specs**
- **File**: `app-release.apk`
- **Size**: ~2-3 MB (optimized)
- **Signed**: Production ready
- **Obfuscated**: Code protection
- **Optimized**: Smaller size, better performance

## 🔧 Troubleshooting Build Issues

### **Error: "SDK not found"**
```bash
# Set Android SDK path
set ANDROID_HOME=C:\Users\%USERNAME%\AppData\Local\Android\Sdk
```

### **Error: "Java not found"**
```bash
# Install JDK 17 dan set JAVA_HOME
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.8.101-hotspot
```

### **Error: "Gradle build failed"**
```bash
# Clean dan rebuild
gradlew.bat clean
gradlew.bat assembleDebug
```

### **Error: "Keystore not found"**
- Buat keystore baru di Android Studio
- Atau gunakan debug keystore untuk testing

## 📱 Install APK ke Android

### **Method 1: ADB (Developer)**
```bash
# Enable USB Debugging di Android
# Connect device via USB
adb install app-debug.apk
```

### **Method 2: File Transfer**
1. Copy APK ke Android storage
2. Buka file manager di Android
3. Tap APK file
4. Allow "Install from Unknown Sources"
5. Install aplikasi

### **Method 3: Cloud Share**
1. Upload APK ke Google Drive/Dropbox
2. Download di Android
3. Install dari Downloads folder

## 🎯 Expected APK Behavior

### **First Launch**
- App icon: Browser symbol
- App name: "Browser APK"
- Main screen: URL input + "Buka di Custom Tab" button

### **Functionality Test**
1. Input: `https://google.com`
2. Tap: "Buka di Custom Tab"
3. Result: Chrome Custom Tab opens with black toolbar
4. Features: Share button, page title, back navigation

### **Fallback Test**
- No Chrome: Opens in default browser
- No browser: Opens in internal WebView
- JavaScript enabled, full navigation controls

## 📊 APK Performance

### **Memory Usage**
- RAM: 15-30 MB
- Storage: 3-5 MB
- Cache: 1-10 MB (depending on usage)

### **Battery Impact**
- Minimal when idle
- Normal consumption during browsing
- Efficient Custom Chrome Tabs implementation

## 🔒 Security Features

### **Debug APK**
- Debuggable: Yes
- Obfuscated: No
- Suitable for: Testing only

### **Release APK**
- Debuggable: No
- Obfuscated: Yes (ProGuard)
- Signed: Production certificate
- Suitable for: Distribution

---

## 🚀 Quick Start Commands

```bash
# Install Android Studio
# Import AndroidBrowserApp project
# Wait for Gradle sync
# Build → Build APK(s)
# Get APK from: app/build/outputs/apk/debug/app-debug.apk
```

**APK siap install di Android! 📱✅**