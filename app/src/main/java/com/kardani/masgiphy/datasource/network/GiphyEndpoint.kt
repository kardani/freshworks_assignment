package com.kardani.masgiphy.datasource.network

import com.kardani.masgiphy.datasource.network.model.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface GiphyEndpoint {

    @GET("trending")
    suspend fun searchGifs(@Query("q") query: String) : ListResponse

    @GET("search")
    suspend fun trendGifs() : ListResponse

}