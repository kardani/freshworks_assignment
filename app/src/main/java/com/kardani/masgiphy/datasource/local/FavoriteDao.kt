package com.kardani.masgiphy.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.kardani.masgiphy.datasource.local.model.GiphEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM giph_entity")
    fun getAll() : Flow<List<GiphEntity>>

    @Query("SELECT * FROM giph_entity WHERE id = :id")
    suspend fun getById(id: String) : GiphEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insert(breed: GiphEntity)

    @Delete
    suspend fun delete(breed: GiphEntity)

}