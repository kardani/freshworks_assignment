package com.kardani.masgiphy.di

import android.content.Context
import androidx.room.Room
import com.kardani.masgiphy.datasource.local.FavoriteDao
import com.kardani.masgiphy.datasource.local.GiphyDatabase
import org.koin.dsl.module

val localPersistenceModule = module{
    factory { provideFavoriteDao(get()) }
    single { provideRoomDataBase(get()) }
}

private fun provideRoomDataBase(context: Context) : GiphyDatabase {
    return Room
        .databaseBuilder(context, GiphyDatabase::class.java, GiphyDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideFavoriteDao(database: GiphyDatabase) : FavoriteDao = database.favoriteDao