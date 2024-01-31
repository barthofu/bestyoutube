package fr.bgili.bestyoutube.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import fr.bgili.bestyoutube.entities.Category
import fr.bgili.bestyoutube.entities.Video

data class CategoryWithVideos(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val videos: List<Video>
)