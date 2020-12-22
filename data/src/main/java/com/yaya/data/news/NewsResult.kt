package com.yaya.data.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResult(var stat: String, var data: MutableList<NewsDetail>) : Parcelable