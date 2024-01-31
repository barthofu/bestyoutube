package fr.bgili.bestyoutube.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "video")
data class Video(

    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "category_id")
    val categoryId: Long

) : BaseEntity, Serializable
