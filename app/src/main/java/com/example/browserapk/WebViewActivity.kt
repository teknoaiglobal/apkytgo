package com.example.browserapk

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * WebViewActivity - Activity fallback menggunakan WebView
 * Digunakan ketika Custom Chrome Tab dan browser default tidak tersedia
 */
class WebViewActivity : AppCompatActivity() {

    // Deklarasi WebView
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        // Inisialisasi WebView
        initializeWebView()
        
        // Ambil URL dari intent dan load
        loadUrlFromIntent()
        
        // Setup action bar dengan tombol back
        setupActionBar()
    }

    /**
     * Inisialisasi dan konfigurasi WebView
     */
    private fun initializeWebView() {
        webView = findViewById(R.id.webView)
        
        // Konfigurasi WebView settings
        webView.settings.apply {
            // Aktifkan JavaScript - Penting untuk website modern
            javaScriptEnabled = true
            
            // Aktifkan DOM storage
            domStorageEnabled = true
            
            // Aktifkan database
            databaseEnabled = true
            
            // Aktifkan zoom controls
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
            
            // Load images otomatis
            loadsImagesAutomatically = true
            
            // Mixed content mode untuk HTTPS/HTTP
            mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        // Set WebViewClient untuk handle navigation dalam WebView
        webView.webViewClient = object : WebViewClient() {
            
            /**
             * Override untuk handle loading halaman dalam WebView
             * Mencegah membuka browser eksternal
             */
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let {
                    view?.loadUrl(it)
                }
                return true
            }

            /**
             * Callback ketika halaman mulai loading
             */
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                super.onPageStarted(view, url, favicon)
                // Bisa tambahkan progress bar di sini jika diperlukan
            }

            /**
             * Callback ketika halaman selesai loading
             */
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Update title dengan judul halaman
                title = view?.title ?: "Browser APK"
            }

            /**
             * Handle error loading halaman
             */
            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                
                // Tampilkan pesan error kepada user
                Toast.makeText(
                    this@WebViewActivity,
                    "Error loading page: $description",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    /**
     * Ambil URL dari intent extra dan load di WebView
     */
    private fun loadUrlFromIntent() {
        val url = intent.getStringExtra("url")
        
        if (url != null && url.isNotEmpty()) {
            try {
                webView.loadUrl(url)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "Error loading URL: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            // Jika tidak ada URL, load halaman default
            webView.loadUrl("https://www.google.com")
        }
    }

    /**
     * Setup action bar dengan tombol back
     */
    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Browser APK"
        }
    }

    /**
     * Handle tombol back - navigasi WebView atau tutup activity
     */
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            // Jika WebView bisa back, navigasi ke halaman sebelumnya
            webView.goBack()
        } else {
            // Jika tidak bisa back lagi, tutup activity
            super.onBackPressed()
        }
    }

    /**
     * Handle action bar back button
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * Cleanup WebView saat activity di-destroy
     */
    override fun onDestroy() {
        super.onDestroy()
        
        // Cleanup WebView untuk mencegah memory leak
        webView.apply {
            clearHistory()
            clearCache(true)
            loadUrl("about:blank")
            onPause()
            removeAllViews()
            destroy()
        }
    }
}