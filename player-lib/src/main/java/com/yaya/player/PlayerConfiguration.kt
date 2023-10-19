package com.yaya.player

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerConfiguration(var uri: Uri?) : Parcelable