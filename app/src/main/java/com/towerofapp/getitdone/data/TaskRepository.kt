package com.towerofapp.getitdone.data

import com.towerofapp.getitdone.data.database.TaskDao
import com.towerofapp.getitdone.data.database.TaskListDao
import com.towerofapp.getitdone.data.model.Task
import com.towerofapp.getitdone.data.model.TaskList
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao, private val taskListDao: TaskListDao) {

    suspend fun createTask(task: Task){
        taskDao.createTask(task)
    }

    fun getAllTask(): Flow<List<Task>> {
        return taskDao.getAllTask()
    }

    fun getStarredTask(): Flow<List<Task>> {
        return taskDao.getStarredTask()
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    fun getAllTaskList(): Flow<List<TaskList>> {
        return taskListDao.getAllTaskList()
    }

    suspend fun createTaskList(listName: String) {
        val taskList = TaskList(id = 0, name = listName)
        taskListDao.createTaskList(taskList)
    }


}