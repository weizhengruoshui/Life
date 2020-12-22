package com.yaya.data.almanac

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlmanacHour(
    var yangli: String,
    var hours: String,
    var des: String,
    var yi: String,
    var ji: String
) : Parcelable