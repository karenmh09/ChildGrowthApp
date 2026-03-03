package com.childgrow.app.model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MeasurementViewModelFactory (val application: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MeasurementViewModel::class.java)) {
            return MeasurementViewModel(application) as T
        } else {
            throw RuntimeException("Failed to create instance of the ${modelClass.simpleName}")
        }
    }
}