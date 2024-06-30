package com.yaya.data.local.news

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getNews(): Single<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity>): Completable

    @Delete
    fun delete(news: List<NewsEntity>): Completable
}