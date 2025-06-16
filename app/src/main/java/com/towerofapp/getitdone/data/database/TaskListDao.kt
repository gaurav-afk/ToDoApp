package com.towerofapp.getitdone.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.towerofapp.getitdone.data.model.TaskList
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskListDao {

    @Insert
    suspend fun createTaskList(task: TaskList)

    @Query("SELECT * FROM task_list")
    fun getAllTaskList(): Flow<List<TaskList>>

    @Query("DELETE FROM task_list")
    suspend fun deleteAllTaskList(): Int

    @Update
    suspend fun updateTask(taskList: TaskList)

    @Delete
    suspend fun deleteTask(taskList: TaskList)
}