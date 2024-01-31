package fr.bgili.bestyoutube.services

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.bgili.bestyoutube.dao.TaskDao
import fr.bgili.bestyoutube.entities.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1)
abstract class DatabaseService : RoomDatabase() {

    // singleton pattern to ease the access to the database
    companion object {
        private var _instance: DatabaseService? = null

        fun getDatabase(applicationContext: android.content.Context): DatabaseService {
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

    // dao accessors
    abstract fun taskDao(): TaskDao

}
