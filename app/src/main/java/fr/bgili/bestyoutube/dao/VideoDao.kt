package fr.bgili.bestyoutube.dao

import androidx.room.Dao
import androidx.room.Query
import fr.bgili.bestyoutube.entities.Video

@Dao
abstract class VideoDao : BaseDao<Video>() {

    @Query("UPDATE video SET favorite = :favorite WHERE id = :id")
    abstract suspend fun updateFavorite(id: Long, favorite: Boolean)
}