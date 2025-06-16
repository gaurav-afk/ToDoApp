package com.towerofapp.getitdone.data.model

import androidx.room.Entity

@Entity(tableName = "task_list")
data class TaskList(
    val id: Int,
    val name: String
)


