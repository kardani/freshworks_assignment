package com.kardani.masgiphy.repository.datasource

import com.kardani.masgiphy.domain.model.Giph
import kotlinx.coroutines.flow.Flow

interface GiphyLocalDataSource {

    fun getFavorites() : Flow<List<Giph>>

    suspend fun isFavorite(giph: Giph) : Boolean

    suspend fun addToFavorite(giph: Giph)

    suspend fun removeFromFavorite(giph: Giph)

}