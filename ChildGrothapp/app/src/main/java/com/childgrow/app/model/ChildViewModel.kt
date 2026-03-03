package com.childgrow.app.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChildViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ChildRepository(application)

    fun getAllChildren(): List<Child> = repository.getAllChildren()

    fun addChild(child: Child) {
        viewModelScope.launch(Dispatchers.IO) { // or lifecycleScope
            repository.insertChild(child)
        }
    }
}
