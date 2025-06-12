package com.towerofapp.getitdone

import android.app.Application
import com.towerofapp.getitdone.data.TaskRepository
import com.towerofapp.getitdone.data.database.GetItDoneDatabase

class GetItDoneApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        val database = GetItDoneDatabase.getDatabase(this)
        val taskDao = database.getTaskDao()
        taskRepository = TaskRepository(taskDao)
    }

    companion object {
        lateinit var taskRepository: TaskRepository
    }
}