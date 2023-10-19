package com.yaya.life.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.yaya.life.R

class RegularNotificationFactory : NotificationFactory() {

    override fun createNotification(context: Context, channelId: String): Notification {
        val intent = Intent(context, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder: Notification.Builder = Notification.Builder(context, channelId)
            .setOnlyAlertOnce(true)
            .setSmallIcon(R.drawable.ic_notifications_black)
            .setContentText(channelId)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        return builder.build()
    }
}