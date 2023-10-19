package com.yaya.life.screen.webview

import android.net.http.SslError
import android.webkit.*

class CustomizedWebViewClient: WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return false
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        com.yaya.utils.LogUtils.logE(javaClass.simpleName, "web view error")
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        super.onReceivedSslError(view, handler, error)
        com.yaya.utils.LogUtils.logE(javaClass.simpleName, "web view ssl error")
    }
}