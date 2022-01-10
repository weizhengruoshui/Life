package com.yaya.utils

import android.util.Log

class LogUtils {

    companion object {
        private const val applicationName: String = "YaYa"
        fun logD(
            tag: String,
            message: String?
        ) {
            if (BuildConfig.DEBUG) {
                Log.d("$applicationName:$tag", message ?: "null")
            }
        }


        fun logE(
            tag: String,
            message: String?
        ) {
            if (BuildConfig.DEBUG) {
                Log.e("$applicationName:$tag", message ?: "null")
            }
        }
    }
}