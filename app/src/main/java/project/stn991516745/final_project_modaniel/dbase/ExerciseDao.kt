package project.stn991516745.final_project_modaniel.dbase

import androidx.room.*

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM CYCLING")
    fun getAllCycle(): List<Cycling>

    @Query("SELECT * FROM WEIGHTS")
    fun getAllWeights(): List<FreeWeights>


    //deleting all users from db
    @Delete
    fun deleteAllCyle(User : List<Cycling>)

    @Delete
    fun deleteAllWeights(User: List<FreeWeights>)

    //Insert one item into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCycle(cycling: Cycling)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeights(weight: FreeWeights)

}