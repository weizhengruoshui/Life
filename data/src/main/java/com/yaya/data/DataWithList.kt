package com.yaya.data

import android.os.Parcelable

data class DataWithList<T : Parcelable>(
    var error_code: Int,
    val reason: String,
    var result: MutableList<T>
)