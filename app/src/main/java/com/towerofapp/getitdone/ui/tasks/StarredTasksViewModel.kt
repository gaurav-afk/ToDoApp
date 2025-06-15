package com.towerofapp.getitdone.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towerofapp.getitdone.GetItDoneApplication
import com.towerofapp.getitdone.data.TaskRepository
import com.towerofapp.getitdone.data.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StarredTasksViewModel : ViewModel() {

    private val repository: TaskRepository = GetItDoneApplication.taskRepository

    fun fetchStarredTask(): Flow<List<Task>> {
        val tasks = repository.getStarredTask()
        return tasks
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}
