package com.example.browserapk

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat

/**
 * MainActivity - Activity utama aplikasi browser
 * Menampilkan input URL dan tombol untuk membuka Custom Chrome Tab
 */
class MainActivity : AppCompatActivity() {

    // Deklarasi komponen UI
    private lateinit var inputUrl: EditText
    private lateinit var btnOpen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi komponen UI
        initializeViews()
        
        // Setup event listener untuk tombol
        setupClickListener()
    }

    /**
     * Inisialisasi view components dari layout
     */
    private fun initializeViews() {
        inputUrl = findViewById(R.id.inputUrl)
        btnOpen = findViewById(R.id.btnOpen)
    }

    /**
     * Setup click listener untuk tombol buka
     */
    private fun setupClickListener() {
        btnOpen.setOnClickListener {
            val url = inputUrl.text.toString().trim()
            
            // Validasi input URL
            if (url.isEmpty()) {
                Toast.makeText(this, "Masukkan URL terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // Format URL jika tidak dimulai dengan http/https
            val formattedUrl = formatUrl(url)
            
            // Buka URL dengan Custom Chrome Tab
            openWithCustomTab(formattedUrl)
        }
    }

    /**
     * Format URL untuk memastikan memiliki protokol yang benar
     */
    private fun formatUrl(url: String): String {
        return if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "https://$url"
        } else {
            url
        }
    }

    /**
     * Membuka URL dengan Custom Chrome Tab
     * Dengan fallback ke browser default dan WebView jika gagal
     */
    private fun openWithCustomTab(url: String) {
        try {
            // Konfigurasi Custom Chrome Tab
            val customTabsIntent = CustomTabsIntent.Builder()
                .setShowTitle(true)  // Tampilkan judul halaman
                .setShareState(CustomTabsIntent.SHARE_STATE_ON)  // Aktifkan tombol share
                .setToolbarColor(ContextCompat.getColor(this, android.R.color.black))  // Warna toolbar hitam
                .build()

            // Coba buka dengan Custom Chrome Tab
            customTabsIntent.launchUrl(this, Uri.parse(url))
            
        } catch (e: ActivityNotFoundException) {
            // Fallback 1: Coba buka dengan browser default
            openWithDefaultBrowser(url)
        } catch (e: Exception) {
            // Fallback 2: Buka dengan WebView internal
            openWithWebView(url)
        }
    }

    /**
     * Fallback 1: Buka dengan browser default menggunakan Intent ACTION_VIEW
     */
    private fun openWithDefaultBrowser(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            
        } catch (e: ActivityNotFoundException) {
            // Jika tidak ada browser yang tersedia, gunakan WebView
            openWithWebView(url)
        } catch (e: Exception) {
            // Jika gagal total, gunakan WebView
            openWithWebView(url)
        }
    }

    /**
     * Fallback 2: Buka dengan WebView internal sebagai opsi terakhir
     */
    private fun openWithWebView(url: String) {
        try {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
            
        } catch (e: Exception) {
            // Jika semua gagal, tampilkan pesan error
            Toast.makeText(
                this, 
                "Tidak dapat membuka URL: ${e.message}", 
                Toast.LENGTH_LONG
            ).show()
        }
    }
}