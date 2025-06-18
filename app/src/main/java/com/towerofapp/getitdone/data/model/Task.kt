package com.towerofapp.getitdone.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "task",
    foreignKeys = [
        ForeignKey(
            entity = TaskList::class,
            parentColumns = ["task_list_id"],
            childColumns = ["list_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Task(
    @PrimaryKey(autoGenerate = true) var taskId: Int = 0,
    var title: String,
    var description: String? = null,
    @ColumnInfo("is_starred") var isStarring: Boolean = false,
    @ColumnInfo("is_complete") var isComplete: Boolean = false,
    @ColumnInfo("list_id") var listId: Int
)
