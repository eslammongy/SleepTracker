
package com.eslam.sleeptracker.sleeptracker

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.eslam.sleeptracker.database.SleepDatabaseDao


class SleepTrackerViewModelFactory(
    private val dataSource: SleepDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {


}

