package com.stephen.combination.common.extension

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.showOrGone(isShow: Boolean) {
    if (isShow) visible() else gone()
}

fun View.showOrInvisible(isShow: Boolean) {
    if (isShow) visible() else invisible()
}

fun View.invisibleOrShow(isInvisible: Boolean) {
    if (isInvisible) invisible() else visibility
}