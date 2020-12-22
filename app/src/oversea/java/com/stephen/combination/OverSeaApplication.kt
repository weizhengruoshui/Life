package com.stephen.combination

import com.stephen.combination.dagger.component.DaggerOverseaAppComponent
import com.stephen.combination.dagger.component.OverseaAppComponent
import com.stephen.combination.dagger.module.FirebaseModule
import com.stephen.combination.firebase.FirebaseAnalyticsAgent
import javax.inject.Inject

class OverSeaApplication : MainApplication() {
    lateinit var overseaAppComponent: OverseaAppComponent

    @Inject
    lateinit var firebaseAnalyticsAgent: FirebaseAnalyticsAgent

    override fun initComponent() {
        super.initComponent()
        overseaAppComponent = DaggerOverseaAppComponent.builder()
            .firebaseModule(FirebaseModule(this))
            .build()
        overseaAppComponent.inject(this)
    }

    override fun initAnalytics() {
        analyticsAgency.setAgent(firebaseAnalyticsAgent)
        analyticsAgency.init()
    }

}