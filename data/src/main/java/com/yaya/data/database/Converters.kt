package com.yaya.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yaya.data.almanac.AlmanacDay
import com.yaya.data.almanac.AlmanacHour
import com.yaya.data.jokes.JokeDetail
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

    @TypeConverter
    fun jsonToJoke(json: String): JokeDetail {
        return Gson().fromJson(json, JokeDetail::class.java)
    }

    @TypeConverter
    fun jsonToAlmanacDay(json: String): AlmanacDay {
        return Gson().fromJson(json, AlmanacDay::class.java)
    }

    @TypeConverter
    fun jsonToAlmanacHours(json: String): List<AlmanacHour> {
        return Gson().fromJson(json, object : TypeToken<List<AlmanacHour>>() {}.type)
    }
}