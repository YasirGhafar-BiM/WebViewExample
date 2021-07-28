package com.techlads.webviewexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techlads.webviewexample.model.Asset
import com.techlads.webviewexample.network.AssetResponse
import com.techlads.webviewexample.utils.Resource
import com.techlads.webviewexample.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //var modelId = "7w7pAfrCfjovwykkEeRFLGw5SXS"
    //var modelId = "442c548d94744641ba279ae94b5f45ec"

    var adapter: DataBindingAssetAdapter? = null
    var viewModel: AssetViewModel? = null
    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        //webView?.webViewClient = WebViewClient();
        setUpAdapter()
        // this will load the url of the website
//        webView?.loadUrl("file:///android_asset/sketchfab.html")
//        webView.addJavascriptInterface(modelId, "modelId")
        // this will enable the javascript settings
        //webView?.settings?.javaScriptEnabled = true
        //bind(webView)
//
//        webView.setWebViewClient(object : WebViewClient() {
//            override fun onPageFinished(view: WebView, url: String) {
//                super.onPageFinished(view, url)
//                webView.loadUrl("javascript:loadmodel('${modelId}')")
//            }
//        })
        // if you want to enable zoom feature
        //webView?.settings?.setSupportZoom(true)

        val factory = ViewModelFactory(AssetsRepository())
        viewModel =ViewModelProvider(this, factory)[AssetViewModel::class.java]

        viewModel?.getModels()
        viewModel!!.assetResponse.observe(this, response)

    }

    val response = Observer<Resource<AssetResponse>> { result ->
        when(result.status) {
            Resource.Status.SUCCESS -> {
                Log.d("RESPONSE::", result.data.toString())
                result.data?.let { onSuccess(it) }
            }
            Resource.Status.ERROR -> {
                Log.d("RESPONSE::", result.data.toString())
            }
            Resource.Status.LOADING -> {

            }
        }
    }

    private fun onSuccess(response: AssetResponse) {
        response.results?.let {
            adapter?.updateAdapter(it)
        }
    }

    fun setUpAdapter() {
        modelsRv.postDelayed({
            adapter = DataBindingAssetAdapter(this)
            modelsRv.adapter = adapter
            modelsRv.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        }, 200)
    }

    fun toWebViewActivity(asset: Asset) {
        WebViewActivity.startActivity(this, asset.embedUrl);
    }
    // if you press Back button this code will work
    /*override fun onBackPressed() {
        // if your webview can go back it will go back
        if (webView.canGoBack())
            webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()

    }*/

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