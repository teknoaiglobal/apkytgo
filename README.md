# Browser APK - Android Application

Aplikasi browser Android sederhana yang menggunakan **Custom Chrome Tabs (CCT)** untuk memberikan pengalaman browsing yang lebih baik.

## 📱 Fitur Utama

- **Custom Chrome Tabs**: Membuka website dengan tampilan yang terintegrasi
- **Fallback System**: Otomatis beralih ke browser default atau WebView jika CCT tidak tersedia
- **UI Modern**: Antarmuka yang bersih dan mudah digunakan
- **Material Design**: Menggunakan komponen Material Design untuk konsistensi

## 🛠️ Spesifikasi Teknis

- **Bahasa**: Kotlin
- **IDE Target**: Android Studio (Arctic Fox atau lebih baru)
- **Minimum SDK**: 21 (Android 5.0)
- **Target SDK**: 34 (Android 14)
- **Dependency Utama**: `androidx.browser:browser:1.8.0`

## 📁 Struktur Project

```
AndroidBrowserApp/
├── app/
│   ├── build.gradle                 # Konfigurasi app module
│   └── src/main/
│       ├── AndroidManifest.xml      # Manifest aplikasi
│       ├── java/com/example/browserapk/
│       │   ├── MainActivity.kt      # Activity utama dengan CCT
│       │   └── WebViewActivity.kt   # Fallback WebView
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml    # Layout activity utama
│           │   └── activity_webview.xml # Layout WebView
│           ├── values/
│           │   └── strings.xml      # String resources
│           └── mipmap-hdpi/
│               └── ic_launcher.xml  # Icon aplikasi
├── build.gradle                    # Konfigurasi project
├── settings.gradle                 # Settings Gradle
└── gradle/wrapper/
    └── gradle-wrapper.properties   # Gradle wrapper
```

## 🚀 Cara Build APK

### 1. Persiapan
1. Buka **Android Studio**
2. Pilih **File > Open** dan pilih folder `AndroidBrowserApp`
3. Tunggu proses sync Gradle selesai

### 2. Jalankan di Emulator/Device
1. Pastikan emulator sudah berjalan atau device terhubung
2. Klik tombol **Run** (▶️) atau tekan `Shift + F10`
3. Pilih device target dan tunggu aplikasi terinstall

### 3. Build APK Debug
1. Pilih **Build > Build Bundle(s) / APK(s) > Build APK(s)**
2. Tunggu proses build selesai
3. APK debug akan tersimpan di: `app/build/outputs/apk/debug/app-debug.apk`

### 4. Build APK Release
1. Pilih **Build > Generate Signed Bundle / APK**
2. Pilih **APK** dan klik **Next**
3. Buat keystore baru atau gunakan yang sudah ada:
   - **Key store path**: Pilih lokasi keystore
   - **Password**: Masukkan password keystore
   - **Key alias**: Nama alias untuk key
   - **Key password**: Password untuk key
4. Pilih **release** build variant
5. Klik **Finish** dan tunggu proses selesai
6. APK release akan tersimpan di: `app/build/outputs/apk/release/app-release.apk`

## 🔧 Konfigurasi Custom Chrome Tabs

Aplikasi ini dikonfigurasi dengan:
- **Toolbar Color**: Hitam (`android.R.color.black`)
- **Show Title**: Aktif untuk menampilkan judul halaman
- **Share State**: Aktif untuk tombol share
- **Close Button Icon**: Icon default Android

## 🔄 Sistem Fallback

1. **Primary**: Custom Chrome Tabs
2. **Secondary**: Intent.ACTION_VIEW (browser default)
3. **Tertiary**: WebView internal dengan JavaScript enabled

## 📝 Komentar Kode

Setiap file kode dilengkapi dengan komentar bahasa Indonesia untuk memudahkan pemahaman:

- **MainActivity.kt**: Implementasi CCT dengan fallback
- **WebViewActivity.kt**: WebView dengan konfigurasi lengkap
- **Layout Files**: Struktur UI dengan Material Design

## 🔒 Permissions

Aplikasi memerlukan permission:
- `INTERNET`: Untuk akses internet
- `ACCESS_NETWORK_STATE`: Untuk cek status koneksi

## 🎨 UI Components

- **EditText**: Input URL dengan validasi
- **MaterialButton**: Tombol dengan style Material Design
- **TextInputLayout**: Container input dengan outline style
- **WebView**: Fallback browser dengan kontrol navigasi

## 📱 Kompatibilitas

- **Minimum Android**: 5.0 (API 21)
- **Target Android**: 14 (API 34)
- **Architecture**: ARM, ARM64, x86, x86_64

## 🐛 Troubleshooting

### Build Error
- Pastikan Android SDK dan build tools ter-update
- Sync project dengan Gradle files
- Clean dan rebuild project

### CCT Tidak Berfungsi
- Pastikan Chrome browser terinstall
- Cek koneksi internet
- Aplikasi akan otomatis fallback ke WebView

### WebView Error
- Pastikan JavaScript enabled
- Cek permission INTERNET di manifest
- Validasi format URL input

## 📄 License

Project ini menggunakan lisensi Apache-2.0.

---

**Developed with ❤️ using Android Studio & Kotlin**