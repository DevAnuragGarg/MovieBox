package com.intact.moviesbox.presentation.viewmodels

import TestDataGenerator
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.intact.moviesbox.domain.entities.NowPlayingMoviesDomainDTO
import com.intact.moviesbox.domain.usecases.GetNowPlayingMoviesUseCase
import com.intact.moviesbox.domain.usecases.GetTopRatedMoviesUseCase
import com.intact.moviesbox.domain.usecases.GetUpcomingMoviesUseCase
import com.intact.moviesbox.domain.usecases.SaveMovieDetailUseCase
import com.intact.moviesbox.presentation.mapper.MovieDomainPresentationMapper
import com.intact.moviesbox.presentation.mapper.NowPlayingDomainPresentationMapper
import com.intact.moviesbox.presentation.mapper.TopRatedDomainPresentationMapper
import com.intact.moviesbox.presentation.mapper.UpcomingDomainPresentationMapper
import com.intact.moviesbox.presentation.model.NowPlayingMoviesDTO
import com.intact.moviesbox.presentation.util.RxImmediateSchedulerRule
import io.reactivex.Observable
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


/**
 * As kotlin classes are final by default and Mockito does not work on final
 * classes, while running this class it will say mockito exception
 * Mockito cannot mock/spy because : final class
 * To resolve this issue mockito has provided a new way: mockito extensions
 * Create a file in the test/resources/mockito-extensions folder called org.mockito.plugins.MockMaker
 * or use testImplementation 'org.mockito:mockito-inline:2.13.0' in build.gradle file
 *
 *
 * Mockito requires you to either use only raw values or only matchers when stubbing a method call.
 * if you want to pass on the real value use eq.
 * when(xyz.query(anyString(), any(Response.class), eq(String.class)).thenReturn("Test") for the fun
 * query(String abc, Response response, Object class)
 *
 *
 * InstantTaskExecutorRule: rule to allow to android components used in our unit tests to be
 * executed synchronously instead of using the background executor used normally.
 *
 * There is a main looper error which pops up: This error occurs because the default scheduler
 * returned by AndroidSchedulers.mainThread() is an instance of LooperScheduler and relies on
 * Android dependencies that are not available in JUnit tests. you can create a custom TestRule
 * that will allow you to reuse the initialization logic across multiple test classes.
 * To resolve this we need to create the rule mentioning the schedulers : RxImmediateSchedulerRule
 */

class FragmentListViewModelTest {

    @get:Rule
    var schedulers = RxImmediateSchedulerRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var fragmentListViewModel: FragmentListViewModel

    @Mock
    lateinit var saveMovieDetailUseCase: SaveMovieDetailUseCase

    @Mock
    lateinit var topRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Mock
    lateinit var upcomingMoviesUseCase: GetUpcomingMoviesUseCase

    @Mock
    lateinit var nowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase

    @Mock
    lateinit var nowPlayingMoviesDomainDTO: NowPlayingMoviesDomainDTO

    @Mock
    lateinit var movieMapper: MovieDomainPresentationMapper

    @Mock
    lateinit var topRatedMoviesMapper: TopRatedDomainPresentationMapper

    @Mock
    lateinit var upcomingMoviesMapper: UpcomingDomainPresentationMapper

    @Mock
    lateinit var nowPlayingMoviesMapper: NowPlayingDomainPresentationMapper

    @Before
    fun setUp() {
        // Mocking all the required variables
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetNowPlayingMoviesSuccess() {
        // creating dummy data
        val nowMoviesList = TestDataGenerator().getNowPlayingMovies()
        val nowPlayingMoviesDTO = NowPlayingMoviesDTO(
            "1",
            "100",
            nowMoviesList
        )

        // when build use case is called then return the dummy result
        `when`(
            nowPlayingMoviesUseCase.buildUseCase(
                GetNowPlayingMoviesUseCase.Param(
                    pageNumber = "1"   // just putting any value of string here
                )
            )
        ).thenReturn(Observable.just(nowPlayingMoviesDomainDTO))

        // when data is getting mapped to desired DTO then return test data
        `when`(nowPlayingMoviesMapper.to(nowPlayingMoviesDomainDTO))
            .thenReturn(nowPlayingMoviesDTO)

        // observing for every value of live data change
        fragmentListViewModel.getNowPlayingMoviesListLiveData().observeForever { /* Do nothing */ }

        // when: call the function of view model
        fragmentListViewModel.getNowPlayingMovies("1")

        // checking the data in the live data
        assertTrue(fragmentListViewModel.getNowPlayingMoviesListLiveData().value == nowMoviesList)
    }
}