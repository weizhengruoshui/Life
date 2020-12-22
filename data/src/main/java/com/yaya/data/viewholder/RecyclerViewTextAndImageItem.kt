package com.yaya.data.viewholder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecyclerViewTextAndImageItem(
    val title: String,
    val text: String,
    val imageUrl: String,
    val authorName: String,
    val date: String,
    val link: String
) : Parcelable