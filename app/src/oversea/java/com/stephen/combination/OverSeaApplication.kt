package com.stephen.combination

import com.stephen.combination.firebase.FirebaseAnalyticsAgent
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