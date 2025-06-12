package com.towerofapp.getitdone.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towerofapp.getitdone.GetItDoneApplication
import com.towerofapp.getitdone.data.model.Task
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = GetItDoneApplication.taskRepository

    fun createTask(title: String, description: String?){
        val task = Task(
            title = title,
            description = description
        )
        viewModelScope.launch {
            repository.createTask(task)
        }
    }
}