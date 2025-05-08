package com.towerofapp.getitdone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) var taskId: Int = 0,
    var title: String,
    var description: String? = null,
    var isStarring: Boolean = false
)
