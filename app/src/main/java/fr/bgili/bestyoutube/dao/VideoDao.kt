package fr.bgili.bestyoutube.dao

import androidx.room.Dao
import fr.bgili.bestyoutube.entities.Video

@Dao
abstract class VideoDao : BaseDao<Video>() {
}