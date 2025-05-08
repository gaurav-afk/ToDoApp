import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.towerofapp.getitdone.Task
import com.towerofapp.getitdone.TaskDao


@Database(entities = [Task::class], version = 1)
abstract class GetItDoneDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao  // after auto-generating GetItDoneDatabaseImpl it's auto-generated TaskDao_Impl is used

    companion object {

        fun createDatabase(context: Context) : GetItDoneDatabase{  // returns GetItDoneDatabaseImpl (auto-generated)
            return Room.databaseBuilder(
                context,
                GetItDoneDatabase::class.java,
                "get-it-done-database"
            ).build()
        }

    }
}



