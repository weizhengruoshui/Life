package com.yaya.data.local.news

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.yaya.data.news.NewsDetail

@Entity(tableName = "news", primaryKeys = ["unique_key"])
data class NewsEntity(
    @ColumnInfo(name = "unique_key")
    val uniqueKey: String,
    @ColumnInfo(name = "time_stamp")
    val timeStamp: Long,
    @ColumnInfo(name = "news_type")
    val newsType: String,
    @ColumnInfo(name = "news_detail")
    val newsDetail: NewsDetail
)