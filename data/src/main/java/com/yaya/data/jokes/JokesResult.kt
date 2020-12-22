package com.yaya.data.jokes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JokesResult(var data: MutableList<JokeDetail>) : Parcelable