package project.stn991516745.final_project_modaniel.dbase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
@Database(entities = [FreeWeights::class,Cycling::class], version = 1)
abstract class ExerciseDatabase: RoomDatabase() {
    abstract fun excerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: ExerciseDatabase? = null
        fun getInstance(context: Context):ExerciseDatabase{
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    ExerciseDatabase::class.java,
                    "exercise.db")
                    .build()
            }
            return INSTANCE as ExerciseDatabase
        }
    }
}