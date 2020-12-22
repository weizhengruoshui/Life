package com.yaya.data.database.jokes

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface JokesDao {

    @Query("SELECT * FROM jokes")
    fun getJokes(): Single<List<JokeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<JokeEntity>): Completable

    @Delete
    fun delete(news: List<JokeEntity>): Completable
}