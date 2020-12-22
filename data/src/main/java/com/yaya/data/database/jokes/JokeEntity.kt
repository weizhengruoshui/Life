package com.yaya.data.database.jokes

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.yaya.data.jokes.JokeDetail

@Entity(tableName = "jokes", primaryKeys = ["hash_id"])
data class JokeEntity(
    @ColumnInfo(name = "hash_id")
    val hashId: String,
    @ColumnInfo(name = "joke_detail")
    val jokeDetail: JokeDetail
)