package com.yaya.data.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDetail(
    var uniquekey: String,
    var title: String,
    var date: String,
    var category: String,
    var author_name: String,
    var url: String,
    var thumbnail_pic_s: String,
    var thumbnail_pic_s02: String,
    var thumbnail_pic_s03: String
) : Parcelable