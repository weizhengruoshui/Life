package com.yaya.life.notification

import android.app.Notification
import android.app.NotificationManager
import android.content.Context

abstract class NotificationFactory {

    abstract fun createNotification(context: Context, channelId: String): Notification

    fun showNotification(context: Context, channelId: String, notificationId: Int) {
        val notification = createNotification(context, channelId)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(notificationId, notification)
    }
}