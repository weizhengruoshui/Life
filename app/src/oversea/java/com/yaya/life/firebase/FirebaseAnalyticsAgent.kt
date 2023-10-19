package com.yaya.life.firebase

import android.app.Application
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.yaya.life.BuildConfig
import com.yaya.life.analytics.AnalyticsAgent
import com.yaya.life.analytics.AnalyticsParameters

/**
 * can't use this class directly,
 * should use AnalyticsAgency instead.
 */
class FirebaseAnalyticsAgent(private val application: Application) : AnalyticsAgent {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun init() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(application)
    }

    override fun logEvent(analyticsParameters: AnalyticsParameters) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, BuildConfig.APPLICATION_ID)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, BuildConfig.VERSION_NAME)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "test")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}