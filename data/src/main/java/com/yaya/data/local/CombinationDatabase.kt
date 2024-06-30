package com.yaya.data.local

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yaya.data.local.news.NewsDao
import com.yaya.data.local.news.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class CombinationDatabase : RoomDatabase() {

    companion object {
        private var instance: CombinationDatabase? = null

        fun getInstance(application: Application): CombinationDatabase {
            if (instance == null) {
                synchronized(CombinationDatabase::class.java) {
                    if (instance == null) {
                        instance = generateAppDatabase(application)
                    }
                }
            }
            return instance!!
        }

        private fun generateAppDatabase(context: Context): CombinationDatabase {
            return Room.databaseBuilder(
                context,
                CombinationDatabase::class.java,
                "database-combination"
            )
                .build()
        }
    }

    abstract fun newsDao(): NewsDao
}
