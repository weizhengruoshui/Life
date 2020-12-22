package com.yaya.data.webview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WebSiteInformation(val siteUrl: String, val siteTitle: String, var siteImage: String?) :
    Parcelable