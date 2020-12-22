package com.yaya.data.database.almanac

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AlmanacDao {
    @Query("SELECT * FROM almanac WHERE date > :queryDate")
    fun getAlmanacInfo(queryDate: String): Single<AlmanacEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(almanacEntity: AlmanacEntity): Completable

    @Delete
    fun delete(almanacEntity: AlmanacEntity): Completable
}