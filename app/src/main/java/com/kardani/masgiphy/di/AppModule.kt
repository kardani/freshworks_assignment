package com.kardani.masgiphy.di

import com.kardani.masgiphy.datasource.local.FavoriteDao
import com.kardani.masgiphy.datasource.local.GiphyLocalDataSourceImpl
import com.kardani.masgiphy.datasource.network.GiphyEndpoint
import com.kardani.masgiphy.datasource.network.GiphyRemoteDataSourceImpl
import com.kardani.masgiphy.domain.GiphyRepository
import com.kardani.masgiphy.repository.GiphyRepositoryImpl
import com.kardani.masgiphy.repository.datasource.GiphyLocalDataSource
import com.kardani.masgiphy.repository.datasource.GiphyRemoteDataSource
import com.kardani.masgiphy.ui.favorites.FavoritesViewModel
import com.kardani.masgiphy.ui.giphs.IndexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {provideLocalDataSource(get())}
    single {provideRemoteDataSource(get())}
    single {provideRepository(get(), get())}
    viewModel { IndexViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }

}

private fun provideRepository(remoteDataSource: GiphyRemoteDataSource, localDataSource: GiphyLocalDataSource) : GiphyRepository {
    return GiphyRepositoryImpl(
        remoteDataSource,
        localDataSource,
    )
}

private fun provideRemoteDataSource(endpoint: GiphyEndpoint) : GiphyRemoteDataSource {
    return GiphyRemoteDataSourceImpl(endpoint)
}

private fun provideLocalDataSource(dao: FavoriteDao) : GiphyLocalDataSource {
    return GiphyLocalDataSourceImpl(dao)
}