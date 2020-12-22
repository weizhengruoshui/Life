package com.yaya.data.viewholder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecyclerViewTextItem(val text: String, val updateTime: String?) : Parcelable