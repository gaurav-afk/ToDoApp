package com.towerofapp.getitdone

import android.app.Application
import com.towerofapp.getitdone.data.GetItDoneDatabase
import com.towerofapp.getitdone.data.TaskDao

class GetItDoneApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        database = GetItDoneDatabase.getDatabase(this)
        taskDao = database.getTaskDao()
    }

    companion object {
        lateinit var database: GetItDoneDatabase
        lateinit var taskDao: TaskDao
    }
}