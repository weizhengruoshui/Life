package com.yaya.data.jokes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JokeDetail(
    var content: String,
    var hashId: String,
    var unixtime: Long,
    var updatetime: String
) : Parcelable