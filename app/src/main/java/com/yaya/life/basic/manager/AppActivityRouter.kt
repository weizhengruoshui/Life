package com.yaya.life.basic.manager

import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import com.yaya.data.webview.WebSiteInformation
import com.yaya.life.basic.AppActivity
import com.yaya.life.screen.scalableimage.ScalableImageActivity
import com.yaya.life.screen.webview.WebViewActivity

class AppActivityRouter {

    companion object {
        fun startActivity(
            context: Context?,
            clazz: Class<out AppActivity>,
            activityOptionsCompat: ActivityOptionsCompat? = null
        ) {
            context?.startActivity(Intent(context, clazz), activityOptionsCompat?.toBundle())
        }

        fun startScalableImageActivity(
            context: Context?,
            imageUrl: String?,
            activityOptionsCompat: ActivityOptionsCompat? = null
        ) {
            val intent = Intent(context, ScalableImageActivity::class.java)
            ScalableImageActivity.initStartIntent(intent, imageUrl ?: "")
            context?.startActivity(intent, activityOptionsCompat?.toBundle())
        }

        fun startWebViewActivity(
            context: Context?,
            linkInfo: WebSiteInformation,
            activityOptionsCompat: ActivityOptionsCompat? = null
        ) {
            val intent = Intent(context, WebViewActivity::class.java)
            WebViewActivity.initStartIntent(intent, linkInfo)
            context?.startActivity(intent, activityOptionsCompat?.toBundle())
        }
    }
}