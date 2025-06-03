package com.towerofapp.getitdone.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Task::class], version = 2)
abstract class GetItDoneDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao  // after auto-generating GetItDoneDatabaseImpl it's auto-generated TaskDao_Impl is used

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



