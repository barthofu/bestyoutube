package fr.bgili.bestyoutube.data.dao

import androidx.room.Dao
import androidx.room.Query
import fr.bgili.bestyoutube.data.entities.Video

@Dao
abstract class VideoDao : BaseDao<Video>() {

    @Query("SELECT * FROM video WHERE favorite = 1")
    abstract suspend fun findAllFavorites(): List<Video>

    @Query("UPDATE video SET favorite = :favorite WHERE id = :id")
    abstract suspend fun updateFavorite(id: Long, favorite: Boolean)

    @Query("DELETE FROM video WHERE id = :id")
    abstract fun deleteById(id: Long)
}