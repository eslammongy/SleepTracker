package com.eslam.sleeptracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {

    abstract val sleepNightDao: SleepDatabaseDao

    companion object {
        @Volatile
        private var dataBaseInstance: SleepDatabase? = null

        fun getDataBaseInstance(context: Context): SleepDatabase {
            synchronized(this) {
                var instance = dataBaseInstance

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    dataBaseInstance = instance
                }

                return instance
            }
        }
    }

}
