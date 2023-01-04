
package com.eslam.sleeptracker.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.eslam.sleeptracker.database.SleepDatabaseDao

class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {
}

