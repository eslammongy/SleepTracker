
package com.eslam.sleeptracker.sleeptracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eslam.sleeptracker.database.SleepDatabaseDao
import java.lang.IllegalArgumentException

class SleepTrackerViewModelFactory(
    private val dataSource: SleepDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SleepTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
