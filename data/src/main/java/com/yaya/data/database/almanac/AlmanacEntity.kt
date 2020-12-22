package com.yaya.data.database.almanac

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.yaya.data.almanac.AlmanacDay
import com.yaya.data.almanac.AlmanacHour

@Entity(tableName = "almanac", primaryKeys = ["date"])
data class AlmanacEntity(
    val date: String,
    @ColumnInfo(name = "almanac_day")
    val almanacDay: AlmanacDay,
    @ColumnInfo(name = "almanac_hours")
    val almanacHours: List<AlmanacHour>
)