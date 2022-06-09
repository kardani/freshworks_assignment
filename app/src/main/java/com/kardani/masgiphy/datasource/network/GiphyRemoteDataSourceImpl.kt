package com.kardani.masgiphy.datasource.network

import com.kardani.masgiphy.core.ResultWrapper
import com.kardani.masgiphy.datasource.network.model.mapToDomain
import com.kardani.masgiphy.domain.model.Giph
import com.kardani.masgiphy.repository.datasource.GiphyRemoteDataSource

class GiphyRemoteDataSourceImpl(
    private val endpoint: GiphyEndpoint
) : GiphyRemoteDataSource {

    override suspend fun getTrendGifs(): ResultWrapper<List<Giph>> {
        return safeApiCall { endpoint.trendGifs().data?.mapToDomain() ?: listOf() }
    }

    override suspend fun searchGifs(query: String): ResultWrapper<List<Giph>> {
        return safeApiCall { endpoint.searchGifs(query).data?.mapToDomain() ?: listOf() }
    }
}