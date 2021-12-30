package project.stn991516745.final_project_modaniel.dbase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
    @Entity(tableName = "weights")
    data class FreeWeights(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "name")
    var fullName: String,
    @ColumnInfo(name = "maxWeightsAmt")
    var maxWeightsAmt: Int,
    @ColumnInfo(name = "time")
    var minutes: Double,
    @ColumnInfo(name = "minWeightsAmt")
    var minWeightsAmt: Int
    )
