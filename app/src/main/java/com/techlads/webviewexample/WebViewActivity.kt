package com.techlads.webviewexample

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*
import java.net.URISyntaxException

val MODEL_URL = "modelUrl"

class WebViewActivity : AppCompatActivity() {

    var modelUrl: String = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView?.webViewClient = MyWebViewClient()

        webView?.settings?.javaScriptEnabled = true

        if (intent.hasExtra(MODEL_URL)) {
                modelUrl = intent.getStringExtra(MODEL_URL).toString()
        }

        if (modelUrl.isNotEmpty()) {
            webView.loadUrl(modelUrl)
            //webView.loadUrl("<body style=\"margin:0px;padding:0px;overflow:hidden\"><iframe src=${modelUrl} id=\"api-frame\" allow=\"autoplay; fullscreen; vr\" allowvr allowfullscreen mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" style=\"overflow:hidden;overflow-x:hidden;overflow-y:hidden;height:100%;width:100%;position:absolute;top:0px;left:0px;right:0px;bottom:0px\" height=\"100%\" width=\"100%\"></iframe></body>")
        }
    }

    inner class MyWebViewClient: WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

            if (url.startsWith("http") || url.startsWith("https")) {
                return false;
            } else if (isAppAvaliable(url)) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            } else if (url.startsWith("intent")) {
                try {
                    val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                        val fallbackUrl = intent.getStringExtra("browser_fallback_url")
                    if (fallbackUrl != null) {
                        view.loadUrl(fallbackUrl);
                        return true;
                    }
                } catch (ex: URISyntaxException) {
                }
            }
            return super.shouldOverrideUrlLoading(view, url)
        }

        fun isAppAvaliable(uri: String): Boolean {
            val pm = packageManager
            try {
                //val _package = pm.getPackageInfo("com.google.ar.core", PackageManager.GET_ACTIVITIES)
                pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
                    return true
            } catch (e: PackageManager.NameNotFoundException) {
                Log.d("PM_Exception", e.message.toString())
            }

            return false
        }

    }

    companion object {
        fun startActivity(@NonNull context: Context, url: String): Intent {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(MODEL_URL, url)
            return intent
        }
    }
}
