package com.towerofapp.getitdone.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.towerofapp.getitdone.data.model.Task
import com.towerofapp.getitdone.data.model.TaskList


@Database(entities = [Task::class, TaskList::class], version = 3)
abstract class GetItDoneDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao  // after auto-generating GetItDoneDatabaseImpl it's auto-generated TaskDao_Impl is used
    abstract fun getTaskListDao(): TaskListDao

    companion object {
        @Volatile
        private var DATABASE_INSTANCE: GetItDoneDatabase? = null

        private val MIGRATION_2_TO_3 = object : Migration(2,3) {
            override fun migrate(db: SQLiteConnection) {
                db.execSQL("""CREATE TABLE IF NOT EXISTS 'task_list'
                    (
                        'task_list_id' INTEGER PRIMARY KEY AUTOINCREMENT NULL,
                        'name' TEXT NOT NULL
                    )""".trimIndent())
            }
        }

        fun getDatabase(context: Context): GetItDoneDatabase {  // returns GetItDoneDatabaseImpl
            return DATABASE_INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    GetItDoneDatabase::class.java,
                    "get-it-done-database"
                ).addMigrations(MIGRATION_2_TO_3)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SQLiteConnection) {
                            super.onCreate(db)
                            db.execSQL("INSERT INTO task_list (name) VALUES ('Tasks')")
                        }
                    })
                    .build()
                DATABASE_INSTANCE = instance
                return instance
            }
        }
    }
}