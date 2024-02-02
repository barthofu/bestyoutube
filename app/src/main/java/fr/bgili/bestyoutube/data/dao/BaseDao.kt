package fr.bgili.bestyoutube.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import fr.bgili.bestyoutube.data.entities.BaseEntity
import java.lang.reflect.ParameterizedType

abstract class BaseDao<T : BaseEntity> {

    fun getTableName(): String? {
        val clazz =
            (javaClass.superclass.genericSuperclass as ParameterizedType)
                .actualTypeArguments[0] as Class<*>
        // tableName = StringUtil.toSnakeCase(clazz.getSimpleName());
        return clazz.simpleName
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entities: List<T>): LongArray

    @Update
    abstract suspend fun update(entity: T)

    @Update
    abstract suspend fun update(entities: List<T>)

    @Delete
    abstract suspend fun delete(entity: T)

    @Delete
    abstract suspend fun delete(entities: List<T>)

    @RawQuery
    protected abstract suspend fun findAll(query: SupportSQLiteQuery): List<T>

    suspend fun findAll(): List<T> {
        val query = SimpleSQLiteQuery("SELECT * FROM " + getTableName())
        return findAll(query)
    }

    @RawQuery
    protected abstract suspend fun findById(query: SupportSQLiteQuery): T

    suspend fun findById(id: Long): T {
        val query = SimpleSQLiteQuery("SELECT * FROM " + getTableName() + " WHERE id = " + id)
        return findById(query)
    }
}