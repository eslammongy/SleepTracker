
package com.eslam.sleeptracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SleepDatabaseDao {

    /* create insert sleep night entity function*/
    @Insert
    suspend fun insertSleepNight(night: SleepNight)

    /* create update sleep night entity function*/
    @Update
    suspend fun updateSleepNight(night: SleepNight)

    /* select single sleep night by id*/
    @Query("SELECT * from daily_sleep_night WHERE nightID = :nightID")
    suspend fun selectSingleNight(nightID: Long):SleepNight

    /* create delete all entities function */
    @Query("DELETE FROM daily_sleep_night")
    suspend fun deleteAllNights()

    /* create function to select all sleep nights*/
    @Query("SELECT * FROM daily_sleep_night ORDER BY nightID DESC")
    suspend fun selectAllNights():LiveData<List<SleepNight>>

    /* create function to select recent sleep night*/
    @Query("SELECT * FROM daily_sleep_night ORDER BY nightID DESC LIMIT 1")
    suspend fun selectRecentNights():SleepNight?


}
