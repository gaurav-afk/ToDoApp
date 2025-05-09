package com.towerofapp.getitdone.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Insert
    fun createTask(task: Task)

    @Query("SELECT * FROM task")
    fun getAllTask(): List<Task>

    @Query("DELETE FROM task")
    fun deleteAllTask(): Int

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}