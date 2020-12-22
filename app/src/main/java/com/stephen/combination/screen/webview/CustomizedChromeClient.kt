package com.stephen.combination.screen.webview

import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient

class CustomizedChromeClient : WebChromeClient() {

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        consoleMessage?.apply {
            com.yaya.utils.LogUtils.logE(
                javaClass.simpleName,
                "${message()} -- From line ${lineNumber()} of ${sourceId()}"
            )
        }
        return super.onConsoleMessage(consoleMessage)
    }
}