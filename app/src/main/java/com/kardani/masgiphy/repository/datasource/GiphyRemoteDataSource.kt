package com.kardani.masgiphy.repository.datasource

import com.kardani.masgiphy.core.ResultWrapper
import com.kardani.masgiphy.domain.model.Giph

interface GiphyRemoteDataSource {

    suspend fun getTrendGifs(): ResultWrapper<List<Giph>>

    suspend fun searchGifs(criteria: String): ResultWrapper<List<Giph>>

}