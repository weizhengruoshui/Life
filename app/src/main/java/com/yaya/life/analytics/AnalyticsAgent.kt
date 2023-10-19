package com.yaya.life.analytics

interface AnalyticsAgent {

    fun init()

    fun logEvent(analyticsParameters: AnalyticsParameters)
}