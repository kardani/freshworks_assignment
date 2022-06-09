package com.kardani.masgiphy.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kardani.masgiphy.domain.GiphyRepository
import com.kardani.masgiphy.ui.model.GiphUI
import com.kardani.masgiphy.ui.model.mapToDomain
import com.kardani.masgiphy.ui.model.mapToView
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: GiphyRepository
) : ViewModel() {

    val favorites: LiveData<List<GiphUI>> = repository
        .getFavorites()
        .map {
            it.mapToView()
        }
        .asLiveData()


    fun removeFromFavorite(giph: GiphUI){

        viewModelScope.launch {

            repository.removeFromFavorite(giph.mapToDomain())

        }
    }


}