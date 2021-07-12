package com.techlads.webviewexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var modelId = "7w7pAfrCfjovwykkEeRFLGw5SXS"

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView?.webViewClient = WebViewClient();

        // this will load the url of the website
        webView?.loadUrl("file:///android_asset/sketch_fab.html")
        webView.addJavascriptInterface(modelId, "modelId")
        // this will enable the javascript settings
        webView?.settings?.javaScriptEnabled = true
        bind(webView)

        webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                webView.loadUrl("javascript:loadmodel()")
            }
        })
        // if you want to enable zoom feature
        //webView?.settings?.setSupportZoom(true)

    }

    // if you press Back button this code will work
    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (webView.canGoBack())
            webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }

    @SuppressLint("AddJavascriptInterface")
    fun bind(webView: WebView) {
        webView.addJavascriptInterface(AndroidJSInterface, "Android")
    }

}


object AndroidJSInterface {
    val TAG = "AndroidJSInterface"
    @JavascriptInterface
    fun onClicked() {
        Log.d(TAG, "Help button clicked")
    }

    @JavascriptInterface
    fun getModelKey(): String {
        return "7w7pAfrCfjovwykkEeRFLGw5SXS";
    }
}