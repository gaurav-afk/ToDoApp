package com.towerofapp.getitdone.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towerofapp.getitdone.GetItDoneApplication
import com.towerofapp.getitdone.data.model.Task
import com.towerofapp.getitdone.data.model.TaskList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = GetItDoneApplication.taskRepository


    fun getTaskLists(): Flow<List<TaskList>> {
        return repository.getAllTaskList()
    }

    fun createTask(title: String, description: String?, listId: Int){
        val task = Task(
            title = title,
            description = description,
            listId = listId
        )
        viewModelScope.launch {
            repository.createTask(task)
        }
    }

    fun addNewTaskList(listName: String) {
        if (listName == null) return
        viewModelScope.launch {
            repository.createTaskList(listName)
        }
    }
}