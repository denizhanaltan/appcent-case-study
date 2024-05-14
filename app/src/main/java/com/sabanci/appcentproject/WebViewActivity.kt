package com.sabanci.appcentproject

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.sabanci.appcentproject.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("url") ?: ""

        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(url)
            settings.javaScriptEnabled = true
        }

        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
