package com.kardani.masgiphy.datasource.local

import com.kardani.masgiphy.datasource.local.model.mapToDomain
import com.kardani.masgiphy.datasource.local.model.mapToEntity
import com.kardani.masgiphy.domain.model.Giph
import com.kardani.masgiphy.repository.datasource.GiphyLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GiphyLocalDataSourceImpl(
    private val favoriteDao: FavoriteDao
) : GiphyLocalDataSource {

    override fun getFavorites(): Flow<List<Giph>> {
        return favoriteDao.getAll().map {
            it.mapToDomain()
        }
    }

    override suspend fun isFavorite(giph: Giph): Boolean {
        return favoriteDao.getById(giph.id) != null
    }

    override suspend fun addToFavorite(giph: Giph) {
        favoriteDao.insert(giph.mapToEntity())
    }

    override suspend fun removeFromFavorite(giph: Giph) {
        favoriteDao.delete(giph.mapToEntity())
    }

}