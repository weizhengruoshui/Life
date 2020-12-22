package com.yaya.data.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.yaya.data.database.almanac.AlmanacDao
import com.yaya.data.database.almanac.AlmanacEntity
import com.yaya.data.database.jokes.JokeEntity
import com.yaya.data.database.jokes.JokesDao
import com.yaya.data.database.news.NewsDao
import com.yaya.data.database.news.NewsEntity

@Database(
    entities = [NewsEntity::class, JokeEntity::class, AlmanacEntity::class],
    version = 3,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class CombinationDatabase : RoomDatabase() {

    companion object {
        private var instance: CombinationDatabase? = null
        private const val migrate1To2Sql =
            "CREATE TABLE `jokes` (`hash_id` TEXT NOT NULL, `joke_detail` TEXT NOT NULL, " +
                    "PRIMARY KEY(`hash_id`))"
        private const val migrate2To3Sql =
            "CREATE TABLE `almanac` (`date` TEXT NOT NULL, `almanac_day` TEXT NOT NULL, " +
                    "`almanac_hours` TEXT NOT NULL, PRIMARY KEY(`hash_id`))"

        private val migration1To2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(migrate1To2Sql)
            }
        }

        private val migration2To3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(migrate2To3Sql)
            }
        }

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
                .addMigrations(migration1To2)
                .addMigrations(migration2To3)
                .build()
        }
    }

    abstract fun newsDao(): NewsDao

    abstract fun jokesDao(): JokesDao

    abstract fun almanacDao(): AlmanacDao
}