package com.eslam.sleeptracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_sleep_night")
data class SleepNight(
    @PrimaryKey(autoGenerate = true)
    var nightID: Long = 0L,
    @ColumnInfo(name="start_time")
    var startTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name="end_time")
    var endTime: Long = startTime,
    @ColumnInfo(name="sleep_quality")
    var sleepQuality: Int = 1
)
