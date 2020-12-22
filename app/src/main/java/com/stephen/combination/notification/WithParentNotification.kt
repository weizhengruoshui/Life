package com.stephen.combination.notification

import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import com.stephen.combination.R
import com.yaya.data.webview.WebSiteInformation
import com.stephen.combination.screen.webview.WebViewActivity
import com.stephen.combination.screen.webview.WebViewModel

class WithParentNotification : NotificationFactory() {

    override fun createNotification(context: Context, channelId: String): Notification {
        val intent = Intent(context, WebViewActivity::class.java)

        intent.putExtra(
            WebViewModel.LINK_INFO,
            WebSiteInformation("www.baidu.com", "authorName", "")
        )
        val pendingIntent = TaskStackBuilder.create(context).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(intent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder: Notification.Builder = Notification.Builder(context, channelId)
            .setOnlyAlertOnce(true)
            .setSmallIcon(R.drawable.ic_notifications_black)
            .setContentIntent(pendingIntent)
            .setContentText(channelId)
            .setAutoCancel(true)
        return builder.build()
    }
}