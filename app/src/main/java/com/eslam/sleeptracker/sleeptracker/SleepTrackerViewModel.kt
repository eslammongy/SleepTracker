
package com.eslam.sleeptracker.sleeptracker

import android.app.Application
import androidx.lifecycle.*
import com.eslam.sleeptracker.database.SleepDatabaseDao
import com.eslam.sleeptracker.database.SleepNight
import com.eslam.sleeptracker.formatNights
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

        private val recentNight = MutableLiveData<SleepNight?>()
        private val allNights = database.selectAllNights()
        private val _navigateToSleepTrackerQuality = MutableLiveData<SleepNight?>()
        val navigateToSleepTrackerQuality: LiveData<SleepNight?>
                get() = _navigateToSleepTrackerQuality

        val nightsToString = Transformations.map(allNights){
                formatNights(it, application.resources)
        }

        init {
            initializeRecentNight()
        }


        private fun   initializeRecentNight(){

                viewModelScope.launch {
                       recentNight.value = getRecentSleepNights()
                }
        }

        private suspend fun getRecentSleepNights():SleepNight?{
                return withContext(Dispatchers.IO){
                        var night = database.selectRecentNights()
                        if (night?.endTimeMilli != night?.startTimeMilli){
                               night = null
                        }
                        night
                }

        }

        fun doneNavigating() {
                _navigateToSleepTrackerQuality.value = null
        }
        private suspend fun insertNewNight(night: SleepNight){
                database.insertSleepNight(night)
        }

        private suspend fun clearData(){
                database.deleteAllNights()
        }

        private suspend fun updateNight(night: SleepNight){
                database.updateSleepNight(night)
        }

        fun startSleepTracking(){
                viewModelScope.launch {
                        val newNight = SleepNight()
                        insertNewNight(newNight)
                        recentNight.value = getRecentSleepNights()
                }
        }

        fun stopSleepTracking(){
                viewModelScope.launch {
                        val oldestNight = recentNight.value ?: return@launch
                        oldestNight.endTimeMilli = System.currentTimeMillis()

                        updateNight(oldestNight)
                        _navigateToSleepTrackerQuality.value = oldestNight

                }
        }

        fun clearNights(){
                viewModelScope.launch {
                        clearData()
                        recentNight.value = null
                }
        }


}

