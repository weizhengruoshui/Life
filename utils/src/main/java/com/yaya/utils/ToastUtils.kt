package com.yaya.utils

import android.content.Context
import android.widget.Toast

class ToastUtils(private val context: Context) {

    fun showShortToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}