package com.towerofapp.getitdone.ui

import androidx.lifecycle.ViewModel
import com.towerofapp.getitdone.GetItDoneApplication
import com.towerofapp.getitdone.data.Task
import kotlin.concurrent.thread

class MainViewModel : ViewModel() {
    private val taskDao = GetItDoneApplication.taskDao

    fun createTask(title: String, description: String?){
        val task = Task(
            title = title,
            description = description
        )
        thread {
            taskDao.createTask(task)
        }
    }
}