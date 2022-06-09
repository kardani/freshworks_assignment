package com.kardani.masgiphy.datasource.network

import com.kardani.masgiphy.datasource.network.model.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface GiphyEndpoint {

    @GET("search")
    suspend fun searchGifs(@Query("q") query: String) : ListResponse

    @GET("trending")
    suspend fun trendGifs() : ListResponse

}