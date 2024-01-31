package fr.bgili.bestyoutube.dao

import androidx.room.Dao
import fr.bgili.bestyoutube.entities.Data
import fr.bgili.bestyoutube.entities.Video

@Dao
abstract class DataDao {

    // WIP

    fun set(key: String, value: String) {
        insert(Data(key, value))
    }

    fun get(key: String): String? {
        return select(key)?.value
    }

    fun set(key: String, value: Boolean) {
        set(key, value.toString())
    }
}

