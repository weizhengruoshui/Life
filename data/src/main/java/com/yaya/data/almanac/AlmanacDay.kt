package com.yaya.data.almanac

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlmanacDay(
    var id: String,
    var yangli: String,
    var yinli: String,
    var wuxing: String,
    var chongsha: String,
    var baiji: String,
    var jishen: String,
    var yi: String,
    var xiongshen: String,
    var ji: String
) :
    Parcelable