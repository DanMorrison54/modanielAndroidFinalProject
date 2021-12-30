package project.stn991516745.final_project_modaniel.dbase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "cycling")
    data class Cycling(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        @ColumnInfo(name = "name")
        var fullName: String,
        @ColumnInfo(name = "distance")
        var distance: Int,
        @ColumnInfo(name = "time")
        var minutes: Double,
        @ColumnInfo(name = "maxSpeed")
        var maxSpeed: Int
    )
