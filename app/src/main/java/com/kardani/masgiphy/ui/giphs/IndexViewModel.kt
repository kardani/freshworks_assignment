package com.kardani.masgiphy.ui.giphs

import androidx.lifecycle.*
import com.kardani.masgiphy.core.DataState
import com.kardani.masgiphy.domain.GiphyRepository
import com.kardani.masgiphy.ui.model.GiphUI
import com.kardani.masgiphy.ui.model.mapToView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class IndexViewModel constructor(private val giphyRepository: GiphyRepository) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val _giphs = MutableStateFlow<DataState<List<GiphUI>>>(DataState.Loading)
    val giphs : LiveData<DataState<List<GiphUI>>> = _giphs.asLiveData()

    init {

        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .flatMapLatest {

                    giphyRepository
                        .getGiphs(it)


                }.collectLatest {

                    when(it){
                        is DataState.Error -> _giphs.emit(DataState.Error(it.error))
                        DataState.Loading -> _giphs.emit(DataState.Loading)
                        is DataState.Success -> _giphs.emit(DataState.Success(it.value.mapToView()))
                    }

                }
        }

    }

    fun searchGiphs(query: String){

        searchQuery.value = query

    }


    override fun onCleared() {
        super.onCleared()
    }

}
