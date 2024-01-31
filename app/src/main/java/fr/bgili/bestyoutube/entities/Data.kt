package fr.bgili.bestyoutube.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "data")
data class Data(

    @PrimaryKey
    @ColumnInfo(name = "key")
    val name: String,

    @ColumnInfo(name = "value")
    val value: String

) : Serializable