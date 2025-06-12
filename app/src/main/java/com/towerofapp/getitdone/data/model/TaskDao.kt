package com.towerofapp.getitdone.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Insert
    suspend fun createTask(task: Task)

    @Query("SELECT * FROM task")
    suspend fun getAllTask(): List<Task>

    @Query("DELETE FROM task")
    suspend fun deleteAllTask(): Int

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}