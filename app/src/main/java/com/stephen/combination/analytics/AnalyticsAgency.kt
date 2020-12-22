package com.stephen.combination.analytics

import javax.inject.Inject

class AnalyticsAgency @Inject constructor() :
    AnalyticsAgent {
    private var analyticsAgent: AnalyticsAgent? = null

    fun setAgent(analyticsAgent: AnalyticsAgent) {
        this.analyticsAgent = analyticsAgent
    }

    override fun init() {
        checkNull()
        analyticsAgent?.init()
    }

    private fun checkNull() {
        if (analyticsAgent == null) {
            com.yaya.utils.LogUtils.logE(javaClass.simpleName,"analyticsAgent can't be null")
        }
    }

    override fun logEvent(analyticsParameters: AnalyticsParameters) {
        checkNull()
        analyticsAgent?.logEvent(analyticsParameters)
    }

}