package com.yaya.life

import com.yaya.life.firebase.FirebaseAnalyticsAgent
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class OverSeaApplication : MainApplication() {

    @Inject
    lateinit var firebaseAnalyticsAgent: FirebaseAnalyticsAgent

    override fun initAnalytics() {
        analyticsAgency.setAgent(firebaseAnalyticsAgent)
        analyticsAgency.init()
    }

}