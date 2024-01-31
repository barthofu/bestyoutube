package fr.bgili.bestyoutube.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.bgili.bestyoutube.dao.CategoryDao
import fr.bgili.bestyoutube.dao.VideoDao
import fr.bgili.bestyoutube.entities.Video

@Database(
    entities = [
        Video::class
    ],
    version = 1)
abstract class DatabaseService : RoomDatabase() {

    // dao accessors
    abstract fun videoDao(): VideoDao
    abstract fun categoryDao(): CategoryDao

    // singleton pattern to ease the access to the database
    companion object {
        @Volatile
        private var _instance: DatabaseService? = null

        fun getInstance(applicationContext: Context): DatabaseService {
            return _instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    applicationContext,
                    DatabaseService::class.java, "todo"
                ).allowMainThreadQueries().build()

                _instance = instance

                instance
            }
        }

    }

}
