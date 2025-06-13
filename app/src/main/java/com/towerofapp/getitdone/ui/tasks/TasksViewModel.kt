package com.towerofapp.getitdone.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towerofapp.getitdone.GetItDoneApplication
import com.towerofapp.getitdone.data.TaskRepository
import com.towerofapp.getitdone.data.model.Task
import kotlinx.coroutines.launch

class TasksViewModel : ViewModel() {

    private val repository: TaskRepository = GetItDoneApplication.taskRepository

    suspend fun fetchAllTask(): List<Task>{
        val tasks = repository.getAllTask()
        return tasks
    }

    fun updateTask(task: Task, onCompleteUpdate:() -> Unit) {
        viewModelScope.launch {
            repository.updateTask(task)
            onCompleteUpdate()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}