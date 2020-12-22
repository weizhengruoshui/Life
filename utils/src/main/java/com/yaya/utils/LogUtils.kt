package com.yaya.utils

import android.util.Log

class LogUtils {

    companion object {
        private const val applicationName: String = "YaYa"
        fun logD(
            tag: String,
            message: String?
        ) = Log.d("$applicationName:$tag", message ?: "null")


        fun logE(
            tag: String,
            message: String?
        ) = Log.e("$applicationName:$tag", message ?: "null")
    }
}