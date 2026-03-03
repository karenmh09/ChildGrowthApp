package com.childgrow.app.model

import android.app.Application
import android.icu.util.MeasureUnit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MeasurementViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MeasurementRepository(application)

    //val measurements: List<Measurement> = repository.getAllMeasurementsByChild(childId)
        //.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    fun getMeasurementsByChild(childId: Int): List<Measurement> {
        return repository.getAllMeasurementsByChild(childId)
    }

    fun addMeasurement(measurement: Measurement) {
        viewModelScope.launch(Dispatchers.IO) { // or lifecycleScope
            repository.insertMeasurement(measurement)
        }
    }
}
