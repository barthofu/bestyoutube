package fr.bgili.bestyoutube.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "category")
data class Category(

    @PrimaryKey
    override val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

) : BaseEntity, Serializable