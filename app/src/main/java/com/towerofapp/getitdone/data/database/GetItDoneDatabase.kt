package com.towerofapp.getitdone.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.towerofapp.getitdone.data.model.Task
import com.towerofapp.getitdone.data.model.TaskList


@Database(entities = [Task::class, TaskList::class], version = 2)
abstract class GetItDoneDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao  // after auto-generating GetItDoneDatabaseImpl it's auto-generated TaskDao_Impl is used
    abstract fun getTaskListDao(): TaskListDao

    companion object {
        @Volatile
        private var DATABASE_INSTANCE: GetItDoneDatabase? = null

        fun getDatabase(context: Context): GetItDoneDatabase {  // returns GetItDoneDatabaseImpl
            return DATABASE_INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    GetItDoneDatabase::class.java,
                    "get-it-done-database"
                ).fallbackToDestructiveMigration(false).build()
                DATABASE_INSTANCE = instance
                return instance
            }
        }
    }
}



