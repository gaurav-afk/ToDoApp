package com.towerofapp.getitdone.data

import com.towerofapp.getitdone.data.model.Task
import com.towerofapp.getitdone.data.model.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun createTask(task: Task){
        taskDao.createTask(task)
    }

    suspend fun getAllTask(): List<Task>{
        return taskDao.getAllTask()
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}