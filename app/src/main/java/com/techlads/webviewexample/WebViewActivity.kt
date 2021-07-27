package com.techlads.webviewexample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.annotation.NonNull
import kotlinx.android.synthetic.main.activity_web_view.*

val MODEL_URL = "modelUrl"

class WebViewActivity : AppCompatActivity() {


    var modelUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView?.webViewClient = WebViewClient();
        webView?.settings?.javaScriptEnabled = true

        if (intent.hasExtra(MODEL_URL)) {
                modelUrl = intent.getStringExtra(MODEL_URL).toString()
        }

        if (modelUrl.isNotEmpty()) {
            webView.loadUrl(modelUrl)
            //webView.loadUrl("<body style=\"margin:0px;padding:0px;overflow:hidden\"><iframe src=${modelUrl} id=\"api-frame\" allow=\"autoplay; fullscreen; vr\" allowvr allowfullscreen mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" style=\"overflow:hidden;overflow-x:hidden;overflow-y:hidden;height:100%;width:100%;position:absolute;top:0px;left:0px;right:0px;bottom:0px\" height=\"100%\" width=\"100%\"></iframe></body>")
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
