package com.yaya.life.common.manager

import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import com.yaya.life.common.AppActivity
import com.yaya.life.screen.scalableimage.ScalableImageActivity
import com.yaya.life.screen.webview.WebViewActivity
import com.yaya.data.webview.WebSiteInformation

class AppActivityRouter {

    companion object {
        fun startActivity(
            context: Context?,
            clazz: Class<out AppActivity<*, *>>,
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