package com.kardani.masgiphy

import android.util.ArraySet
import com.kardani.masgiphy.domain.model.Giph
import com.kardani.masgiphy.repository.datasource.GiphyLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeGiphyLocalDataSource : GiphyLocalDataSource {

    private val _tempSharedFlow: MutableStateFlow<List<Giph>> = MutableStateFlow(listOf())

    override fun getFavorites(): Flow<List<Giph>> {
        return _tempSharedFlow
    }

    override suspend fun isFavorite(giph: Giph): Boolean {
        return _tempSharedFlow.value.firstOrNull { it.id == giph.id } != null
    }

    override suspend fun addToFavorite(giph: Giph) {

        val newList = ArraySet<Giph>()
        newList.addAll(_tempSharedFlow.value)
        newList.add(giph)

        _tempSharedFlow.value = newList.toList()
    }

    override suspend fun removeFromFavorite(giph: Giph) {
        val newList = _tempSharedFlow.value.filter {
            it.id != giph.id
        }

        _tempSharedFlow.value = newList.toList()
    }

    fun setDefaultData(list: List<Giph>){
        _tempSharedFlow.value = list
    }

}