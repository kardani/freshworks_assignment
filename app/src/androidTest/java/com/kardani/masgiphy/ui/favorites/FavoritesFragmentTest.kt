package com.kardani.masgiphy.ui.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.kardani.masgiphy.FakeGiphyLocalDataSource
import com.kardani.masgiphy.R
import com.kardani.masgiphy.TestApp
import com.kardani.masgiphy.domain.GiphyRepository
import com.kardani.masgiphy.repository.GiphyRepositoryImpl
import com.kardani.masgiphy.repository.datasource.GiphyLocalDataSource
import com.kardani.masgiphy.ui.RecyclerViewItemCountAssertion
import com.kardani.masgiphy.ui.getRandomGiph
import com.kardani.masgiphy.ui.model.mapToView
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module


@RunWith(AndroidJUnit4::class)
@MediumTest
class FavoritesFragmentTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var localDataSource: GiphyLocalDataSource

    private lateinit var repository: GiphyRepository

    private lateinit var viewModel: FavoritesViewModel

    private lateinit var scenario: FragmentScenario<FavoritesFragment>

    private val app = ApplicationProvider.getApplicationContext<TestApp>()

    private val module = module {
        viewModel { viewModel }
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        localDataSource = FakeGiphyLocalDataSource()

        repository = GiphyRepositoryImpl(mockk(), localDataSource)

        viewModel = FavoritesViewModel(repository)

        app.loadModules(module)

        scenario = launchFragmentInContainer(
            themeResId = R.style.AppTheme
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
        unloadKoinModules(module)
    }

    @Test
    fun when_favorites_variable_returns_data_then_recyclerview_displays_them(){
        runBlocking {

            val firstGiph = getRandomGiph()

            localDataSource.addToFavorite(firstGiph)
            localDataSource.addToFavorite(getRandomGiph())

            Espresso.onView(withId(R.id.favorites_rv))
                .check(RecyclerViewItemCountAssertion(2))
        }

    }

    @Test
    fun when_remove_item_from_then_favorites_variable_affected(){
        runBlocking {

            val firstGiph = getRandomGiph()

            val list = listOf(
                firstGiph,
                getRandomGiph()
            )

            (localDataSource as FakeGiphyLocalDataSource).setDefaultData(list)

            Espresso.onView(withId(R.id.favorites_rv))
                .check(RecyclerViewItemCountAssertion(2))

            viewModel.removeFromFavorite(firstGiph.mapToView())

            delay(200)

            Espresso.onView(withId(R.id.favorites_rv))
                .check(RecyclerViewItemCountAssertion(1))

        }

    }

    @Test
    fun when_favorites_variable_returns_not_empty_then_not_data_label_is_not_visible(){
        runBlocking {

            localDataSource.addToFavorite(getRandomGiph())

            Espresso.onView(withId(R.id.favorites_rv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

            Espresso.onView(withId(R.id.no_data_tv))
                .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))

        }

    }

    @Test
    fun when_favorites_variable_returns_empty_then_not_data_label_is_visible(){
        runBlocking {

            Espresso.onView(withId(R.id.favorites_rv))
                .check(RecyclerViewItemCountAssertion(0))

            Espresso.onView(withId(R.id.no_data_tv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        }

    }

}