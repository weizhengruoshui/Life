package com.yaya.data.viewholder

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RecyclerViewVideoItem(val videoUrl: Uri, val name: String, val type: String) : Parcelable