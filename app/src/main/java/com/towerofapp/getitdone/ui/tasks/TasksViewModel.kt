package com.towerofapp.getitdone.ui.tasks

import androidx.lifecycle.ViewModel
import com.towerofapp.getitdone.GetItDoneApplication
import com.towerofapp.getitdone.GetItDoneApplication.Companion
import com.towerofapp.getitdone.data.GetItDoneDatabase
import com.towerofapp.getitdone.data.Task
import kotlin.concurrent.thread

class TasksViewModel : ViewModel() {

    private val taskDao = GetItDoneApplication.taskDao

    fun fetchTask(callback: (List<Task>) -> Unit){
        thread {
            val tasks = Companion.taskDao.getAllTask()
            callback(tasks)
        }
    }

    fun updateTask(task: Task){
        thread {
            taskDao.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        thread {
           taskDao.deleteTask(task)
        }
    }
}