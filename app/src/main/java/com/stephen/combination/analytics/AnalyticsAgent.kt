package com.stephen.combination.analytics

interface AnalyticsAgent {

    fun init()

    fun logEvent(analyticsParameters: AnalyticsParameters)
}