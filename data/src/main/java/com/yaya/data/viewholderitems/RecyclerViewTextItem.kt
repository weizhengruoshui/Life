package com.yaya.data.viewholderitems

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecyclerViewTextItem(val text: String, val updateTime: String?) : Parcelable