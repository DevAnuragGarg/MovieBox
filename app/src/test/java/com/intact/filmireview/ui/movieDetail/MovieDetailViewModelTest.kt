package com.intact.filmireview.ui.movieDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.intact.filmireview.data.DataManager
import com.intact.filmireview.data.model.MovieDetailDTO
import com.intact.filmireview.util.scheduler.BaseSchedulerProvider
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import timber.log.Timber

/**
 * Created by Anurag Garg on 2019-05-31.
 */
@RunWith(JUnit4::class)
class MovieDetailViewModelTest {

    // A JUnit Test Rule that swaps the background executor used by the Architecture
    // Components with a different one which executes each task synchronously.
    // You can use this rule for your host side tests that use Architecture Components.
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var baseDataManager: DataManager

    @Mock
    lateinit var baseSchedulerProvider: BaseSchedulerProvider

    lateinit var movieDetailViewModel: MovieDetailViewModel


    @Before
    fun setUp() {
        //The method with Before annotation will be run before all tests, in this method
        // we will create the class instance to test (MainViewModel) and we will initialize
        // all mocked elements with the function: MockitoAnnotations.initMocks(this)
        MockitoAnnotations.initMocks(this)
        this.movieDetailViewModel = MovieDetailViewModel(baseDataManager, CompositeDisposable(), baseSchedulerProvider)
        Timber.d("")
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getMovieDetails() {

        Mockito.`when`(this.movieDetailViewModel.getBaseDataManager().getMovieDetails(ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<MovieDetailDTO>())
        }

        // Attach fake observer
        val observer = mock(Observer::class.java) as Observer<MovieDetailDTO>
        this.movieDetailViewModel.getMovieDetailLiveData().observeForever(observer)

        // Invoke
        this.movieDetailViewModel.getMovieDetails("10")

        // Verify
        assertNotNull(this.movieDetailViewModel.getMovieDetailLiveData().value)
    }

    @Test
    fun getMovieDetailLiveData() {
    }

    @Test
    fun getErrorLiveData() {
    }
}