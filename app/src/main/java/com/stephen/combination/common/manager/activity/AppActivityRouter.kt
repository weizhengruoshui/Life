package com.stephen.combination.common.manager.activity

import android.content.Context
import android.content.Intent
import com.yaya.data.webview.WebSiteInformation
import com.stephen.combination.common.AppActivity
import com.stephen.combination.screen.scalableimage.ScalableImageActivity
import com.stephen.combination.screen.webview.WebViewActivity

class AppActivityRouter {

    companion object {
        fun startActivity(context: Context?, clazz: Class<out AppActivity<*, *>>) {
            context?.startActivity(Intent(context, clazz))
        }

        fun startScalableImageActivity(context: Context?, imageUrl: String?) {
            val intent = Intent(context, ScalableImageActivity::class.java)
            ScalableImageActivity.initStartIntent(intent, imageUrl ?: "")
            context?.startActivity(intent)
        }

        fun startWebViewActivity(context: Context?, linkInfo: WebSiteInformation) {
            val intent = Intent(context, WebViewActivity::class.java)
            WebViewActivity.initStartIntent(intent, linkInfo)
            context?.startActivity(intent)
        }
    }
}