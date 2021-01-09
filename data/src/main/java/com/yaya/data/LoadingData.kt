package com.yaya.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoadingData(val type: String) : Parcelable
