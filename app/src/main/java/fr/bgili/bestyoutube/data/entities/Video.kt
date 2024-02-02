package fr.bgili.bestyoutube.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "video")
data class Video(

    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean

) : BaseEntity, Serializable
