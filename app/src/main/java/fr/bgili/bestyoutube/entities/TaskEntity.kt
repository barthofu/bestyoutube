package fr.bgili.bestyoutube.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task")
data class TaskEntity(

    @PrimaryKey override val id: Int,

    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "urgency") val urgency: String

) : BaseEntity(), Serializable
