package com.example.amtron

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {


    lateinit var dialogHelper: DialogHelper


    private lateinit var webView: WebView
    private val URL = "https://tokapoisa.in/web/?url=home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.web)

        supportActionBar?.hide()


        dialogHelper = DialogHelper()

        if(!isOnline(this)){
            dialogHelper.showInternetDialog(this)

            Timer().schedule(timerTask {

                dialogHelper.cancelInternetDialog()
            }, 2000)
        }else{

            dialogHelper.showLoadingDialog(this)

            webView.apply {
                loadUrl(URL)
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true

                Timer().schedule(timerTask {
                    dialogHelper.cancelLoadingDialog()
                }, 2000)
            }
        }






        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (webView.canGoBack()) {
                    webView.goBack()
                } else finish()

            }
        }

        onBackPressedDispatcher.addCallback(callback)
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}