package com.kardani.masgiphy.ui.giphs

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kardani.masgiphy.core.DataState
import com.kardani.masgiphy.core.ResultWrapper
import com.kardani.masgiphy.domain.GiphyRepository
import com.kardani.masgiphy.repository.GiphyRepositoryImpl
import com.kardani.masgiphy.repository.datasource.GiphyLocalDataSource
import com.kardani.masgiphy.repository.datasource.GiphyRemoteDataSource
import com.kardani.masgiphy.ui.createTestObserver
import com.kardani.masgiphy.ui.model.mapToView
import com.kardani.masgiphy.ui.searchedList
import com.kardani.masgiphy.ui.trendingList
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class IndexViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var remoteDataSource: GiphyRemoteDataSource

    @MockK
    private lateinit var localDataSource: GiphyLocalDataSource

    private lateinit var repository: GiphyRepository

    private lateinit var indexViewModel: IndexViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        repository = GiphyRepositoryImpl(remoteDataSource, localDataSource)


        setupRepositoryDefaultResponses()

        indexViewModel = IndexViewModel(repository)

    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    private fun setupRepositoryDefaultResponses(){

        coEvery { remoteDataSource.getTrendGifs() } returns ResultWrapper.Success(trendingList)
        coEvery { remoteDataSource.searchGifs("") } returns ResultWrapper.Success(listOf())

    }

    @Test
    fun `when repository search query is empty then giphs variable updates by trending giphs`() {

        coEvery { localDataSource.isFavorite(any()) } returns true

        val expected = trendingList.map { it.copy(favorite = true) }.mapToView()

        val observer = indexViewModel.giphs.createTestObserver()

        indexViewModel.searchGiphs("")

        val history = observer
            .awaitValue()
            .assertHasValue()
            .awaitNextValue()
            .valueHistory()

        assertThat(history).hasSize(2)
        assertThat(history[0]).isInstanceOf(DataState.Loading::class.java)
        assertThat(history[1]).isEqualTo(DataState.Success(expected))

    }

    @Test
    fun `when repository search query is not empty then giphs variable updates by searched giphs`() {

        val query = "a"

        coEvery { remoteDataSource.searchGifs("a") } returns ResultWrapper.Success(searchedList)

        coEvery { localDataSource.isFavorite(any()) } returns true

        val expected = searchedList.map { it.copy(favorite = true) }.mapToView()

        val observer = indexViewModel.giphs.createTestObserver()

        indexViewModel.searchGiphs(query)

        val history = observer
            .awaitValue()
            .assertHasValue()
            .awaitNextValue()
            .valueHistory()

        assertThat(history).hasSize(2)
        assertThat(history[0]).isInstanceOf(DataState.Loading::class.java)
        assertThat(history[1]).isEqualTo(DataState.Success(expected))

    }



    @Test
    fun `when giph add to favorites then giphs variable will update`() {

        coEvery { localDataSource.isFavorite(any()) } returns false
        coEvery { localDataSource.addToFavorite(any()) } returns Unit

        val expected = trendingList.map { it.copy(favorite = false) }.mapToView()

        val observer = indexViewModel.giphs.createTestObserver()

        indexViewModel.searchGiphs("")

        observer
            .awaitValue()
            .assertHasValue()
            .assertValue(DataState.Loading)
            .awaitNextValue()
            .assertValue(DataState.Success(expected))

        indexViewModel.toggleFavorite(expected[0])

        val expectedFavorite = expected[0].copy(favorite = true)

        val actual = observer
            .awaitValue()
            .value()

        assertThat(actual).isInstanceOf(DataState.Success::class.java)
        assertThat((actual as DataState.Success).value).hasSize(2)
        assertThat(actual.value[0]).isEqualTo(expectedFavorite)

    }

}