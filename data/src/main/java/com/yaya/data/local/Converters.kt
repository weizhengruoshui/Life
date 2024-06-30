package com.yaya.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.yaya.data.news.NewsDetail

class Converters {

    @TypeConverter
    fun objectToJson(any: Any): String {
        return Gson().toJson(any)
    }

    @TypeConverter
    fun jsonToNews(json: String): NewsDetail {
        return Gson().fromJson(json, NewsDetail::class.java)
    }
}
