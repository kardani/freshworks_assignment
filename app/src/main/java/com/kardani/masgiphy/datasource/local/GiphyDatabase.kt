package com.kardani.masgiphy.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kardani.masgiphy.datasource.local.model.GiphEntity

@Database(
    entities = [GiphEntity::class],
    version = 1
)
abstract class GiphyDatabase : RoomDatabase() {

    abstract val favoriteDao: FavoriteDao

    companion object{
        const val DATABASE_NAME = "mas_giphy_db"
    }
}