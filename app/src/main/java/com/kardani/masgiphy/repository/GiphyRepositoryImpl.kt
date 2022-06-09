package com.kardani.masgiphy.repository

import com.kardani.masgiphy.core.DataState
import com.kardani.masgiphy.core.ResultWrapper
import com.kardani.masgiphy.domain.GiphyRepository
import com.kardani.masgiphy.domain.model.Giph
import com.kardani.masgiphy.repository.datasource.GiphyLocalDataSource
import com.kardani.masgiphy.repository.datasource.GiphyRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GiphyRepositoryImpl(
    private val remoteDataSource: GiphyRemoteDataSource,
    private val localDataSource: GiphyLocalDataSource,
) : GiphyRepository {

    override fun getGiphs(query: String): Flow<DataState<List<Giph>>> {

        return flow {

            emit(DataState.Loading)

            val response = if(query.isEmpty()){
                remoteDataSource.getTrendGifs()
            }else{
                remoteDataSource.searchGifs(query = query)
            }

            when(response){
                is ResultWrapper.GenericError -> emit(DataState.Error(response.error))
                ResultWrapper.NetworkError -> emit(DataState.Error("Network Error"))
                is ResultWrapper.Success -> {

                    emit(
                        DataState.Success(
                            response.value.map {

                                it.copy(favorite = localDataSource.isFavorite(it))

                            }
                        )
                    )
                }
            }

        }

    }

    override fun getFavorites(): Flow<List<Giph>> {
        return localDataSource.getFavorites()
    }

    override suspend fun isFavorite(giph: Giph): Boolean {
        return localDataSource.isFavorite(giph)
    }

    override suspend fun addToFavorite(giph: Giph) {
        localDataSource.addToFavorite(giph)
    }

    override suspend fun removeFromFavorite(giph: Giph) {
        localDataSource.removeFromFavorite(giph)
    }

}