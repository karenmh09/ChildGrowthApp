package com.childgrow.app.model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ChildViewModelFactory (val application: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChildViewModel::class.java)) {
            return ChildViewModel(application) as T
        } else {
            throw RuntimeException("Failed to create instance of the ${modelClass.simpleName}")
        }
    }
}