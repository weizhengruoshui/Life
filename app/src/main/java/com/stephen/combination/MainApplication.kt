package com.stephen.combination

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.stephen.combination.analytics.AnalyticsAgency
import com.stephen.combination.notification.NotificationData
import javax.inject.Inject

abstract class MainApplication : Application() {

    @Inject
    lateinit var analyticsAgency: AnalyticsAgency

    override fun onCreate() {
        super.onCreate()
        initAnalytics()
        initNotificationChannel()
    }

    /**
     * in this method, you have to use
     * analyticsAgency.setAgent method to set
     * the agent of analyticsAgency
     */
    abstract fun initAnalytics()


    private fun initNotificationChannel() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(
            createNotificationChannel(
                "Regular Notification",
                "This is a regular notification.",
                NotificationData.REGULAR_CHANNEL_ID
            )
        )

        notificationManager.createNotificationChannel(
            createNotificationChannel(
                "With Parent Notification",
                "This is a parent notification.",
                NotificationData.WITH_PARENT_CHANNEL_ID
            )
        )
    }

    private fun createNotificationChannel(
        name: String,
        descriptionText: String,
        channelId: String
    ): NotificationChannel {
        return NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = descriptionText
        }
    }

}