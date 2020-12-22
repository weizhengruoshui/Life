package com.yaya.data.oneiromancy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OneiromancyDetail(
    val id: String,
    val title: String,
    val des: String,
    val list: List<String>
) : Parcelable