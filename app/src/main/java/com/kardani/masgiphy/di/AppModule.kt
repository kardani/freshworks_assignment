package com.kardani.masgiphy.di

import com.kardani.masgiphy.datasource.network.GiphyEndpoint
import com.kardani.masgiphy.datasource.network.GiphyRemoteDataSourceImpl
import com.kardani.masgiphy.domain.GiphyRepository
import com.kardani.masgiphy.repository.GiphyRepositoryImpl
import com.kardani.masgiphy.repository.datasource.GiphyRemoteDataSource
import com.kardani.masgiphy.ui.giphs.IndexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {provideRemoteDataSource(get())}
    single {provideRepository(get())}
    viewModel { IndexViewModel(get()) }

}

private fun provideRepository(giphyRemoteDataSource: GiphyRemoteDataSource) : GiphyRepository {
    return GiphyRepositoryImpl(
        giphyRemoteDataSource)
}

private fun provideRemoteDataSource(endpoint: GiphyEndpoint) : GiphyRemoteDataSource {
    return GiphyRemoteDataSourceImpl(endpoint)
}