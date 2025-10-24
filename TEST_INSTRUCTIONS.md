# 🧪 Test Instructions - Android Browser APK

## ⚠️ Prerequisites untuk Testing

Untuk menjalankan dan test aplikasi Android Browser APK ini, Anda memerlukan:

### 1. **Java Development Kit (JDK)**
- **Download**: [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) atau [OpenJDK 17](https://adoptium.net/)
- **Install**: Ikuti wizard instalasi
- **Set JAVA_HOME**: Tambahkan ke environment variables
- **Test**: Buka Command Prompt baru dan jalankan `java -version`

### 2. **Android Studio**
- **Download**: [Android Studio](https://developer.android.com/studio)
- **Install**: Ikuti wizard instalasi lengkap
- **SDK**: Pastikan Android SDK terinstall (biasanya otomatis)

## 🚀 Cara Test Running

### **Metode 1: Menggunakan Android Studio (Recommended)**

1. **Buka Android Studio**
2. **Open Project**: 
   - File → Open
   - Pilih folder `AndroidBrowserApp`
   - Klik OK
3. **Sync Project**: 
   - Tunggu proses "Gradle Sync" selesai
   - Jika ada error, klik "Try Again" atau "Sync Now"
4. **Setup Emulator**:
   - Tools → AVD Manager
   - Create Virtual Device
   - Pilih device (misal: Pixel 4)
   - Pilih system image (misal: API 30)
   - Finish dan Start emulator
5. **Run Application**:
   - Klik tombol **Run** (▶️) atau tekan `Shift + F10`
   - Pilih emulator yang sudah berjalan
   - Tunggu aplikasi terinstall dan berjalan

### **Metode 2: Command Line (Jika Java sudah terinstall)**

```bash
# Masuk ke directory project
cd AndroidBrowserApp

# Build debug APK
./gradlew assembleDebug

# Install ke device/emulator yang terhubung
./gradlew installDebug
```

## 📱 Testing Scenarios

### **Test 1: Basic Functionality**
1. Buka aplikasi "Browser APK"
2. Masukkan URL: `https://www.google.com`
3. Klik tombol "Buka di Custom Tab"
4. **Expected**: Custom Chrome Tab terbuka dengan Google

### **Test 2: URL Validation**
1. Masukkan URL tanpa protocol: `google.com`
2. Klik tombol "Buka"
3. **Expected**: Aplikasi otomatis menambahkan `https://`

### **Test 3: Fallback System**
1. Uninstall Chrome browser dari device/emulator
2. Masukkan URL: `https://github.com`
3. Klik tombol "Buka"
4. **Expected**: Terbuka di browser default atau WebView

### **Test 4: WebView Fallback**
1. Disable semua browser di device
2. Masukkan URL: `https://stackoverflow.com`
3. Klik tombol "Buka"
4. **Expected**: Terbuka di WebView internal dengan kontrol navigasi

## 🔧 Troubleshooting

### **Error: "java command not found"**
- Install JDK dan set JAVA_HOME environment variable
- Restart Command Prompt/Terminal

### **Error: "Gradle sync failed"**
- Pastikan internet connection aktif
- File → Invalidate Caches and Restart
- Coba sync ulang

### **Error: "No connected devices"**
- Start Android emulator dari AVD Manager
- Atau hubungkan device Android dengan USB debugging enabled

### **Error: "App crashes on launch"**
- Check Logcat di Android Studio untuk error details
- Pastikan minimum SDK device >= 21 (Android 5.0)

## 📊 Expected Test Results

### ✅ **Successful Test Results:**
- Aplikasi launch tanpa crash
- UI tampil dengan EditText dan Button
- Custom Chrome Tab buka dengan toolbar hitam
- Share button dan title aktif di CCT
- Fallback system bekerja jika Chrome tidak ada
- WebView berfungsi dengan JavaScript enabled

### ❌ **Possible Issues:**
- Chrome tidak terinstall → Fallback ke browser default
- Tidak ada browser → Fallback ke WebView
- Invalid URL → Error message atau auto-correction
- Network error → Error handling di WebView

## 📝 Test Report Template

```
=== ANDROID BROWSER APK TEST REPORT ===

Date: ___________
Tester: ___________
Device/Emulator: ___________
Android Version: ___________

TEST RESULTS:
[ ] App launches successfully
[ ] UI elements display correctly
[ ] URL input accepts text
[ ] "Buka" button responds to tap
[ ] Custom Chrome Tab opens
[ ] CCT has black toolbar
[ ] CCT shows page title
[ ] CCT has share button
[ ] Fallback to default browser works
[ ] WebView fallback works
[ ] JavaScript enabled in WebView
[ ] Back button navigation works

ISSUES FOUND:
_________________________________
_________________________________

OVERALL RATING: ___/10
```

## 🎯 Performance Testing

### **Memory Usage**
- Monitor di Android Studio Profiler
- Expected: < 50MB RAM usage

### **Battery Usage**
- Test dengan extended browsing session
- Expected: Normal battery consumption

### **Network Usage**
- Monitor data usage saat browsing
- Expected: Sesuai dengan website yang dibuka

---

**Happy Testing! 🚀**