
package com.eslam.sleeptracker.sleepquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslam.sleeptracker.database.SleepDatabaseDao
import kotlinx.coroutines.launch

class SleepQualityViewModel(private val sleepNightId: Long = 0L, val databaseDao: SleepDatabaseDao):ViewModel(){

    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()
    val navigateToSleepTracker: LiveData<Boolean?>
    get() = _navigateToSleepTracker

    /**
     * Call this immediately after navigating to [SleepTrackerFragment]
     */
    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    /**
     * Sets the sleep quality and updates the database.
     * Then navigates back to the SleepTrackerFragment.
     */
    fun setSleepQuality(quality: Int) {
        viewModelScope.launch {
            val tonight = databaseDao.selectSingleNight(sleepNightId) ?: return@launch
            tonight.sleepQuality = quality
            databaseDao.updateSleepNight(tonight)
            _navigateToSleepTracker.value = true
        }
    }

}