package com.yaya.life

import android.app.Application
import com.yaya.life.analytics.AnalyticsAgency
import javax.inject.Inject

abstract class MainApplication : Application() {

    @Inject
    lateinit var analyticsAgency: AnalyticsAgency

    override fun onCreate() {
        super.onCreate()
        initAnalytics()
    }

    /**
     * in this method, you have to use
     * analyticsAgency.setAgent method to set
     * the agent of analyticsAgency
     */
    abstract fun initAnalytics()
}
