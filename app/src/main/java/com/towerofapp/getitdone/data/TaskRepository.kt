package com.towerofapp.getitdone.data

import com.towerofapp.getitdone.data.model.Task
import com.towerofapp.getitdone.data.model.TaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun createTask(task: Task){
        taskDao.createTask(task)
    }

    fun getAllTask(): Flow<List<Task>> {
        return taskDao.getAllTask()
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}