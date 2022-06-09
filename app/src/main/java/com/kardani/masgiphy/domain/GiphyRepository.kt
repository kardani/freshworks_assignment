package com.kardani.masgiphy.domain

import com.kardani.masgiphy.core.DataState
import com.kardani.masgiphy.domain.model.Giph
import kotlinx.coroutines.flow.Flow

interface GiphyRepository {

    fun getGiphs(query: String): Flow<DataState<List<Giph>>>

}