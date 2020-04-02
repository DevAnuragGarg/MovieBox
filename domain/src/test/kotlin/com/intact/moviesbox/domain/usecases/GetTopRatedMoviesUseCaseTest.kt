package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetTopRatedMoviesUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var baseSchedulerProvider: BaseSchedulerProvider

    private lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    /**
     * if any setup is required for the test it can be done @Before
     * this will prepare whatever is required before starting the tests
     */
    @Before
    fun setUp() {
        // initializing mockito
        MockitoAnnotations.initMocks(this)

        // initializing use case
        getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(movieRepository, baseSchedulerProvider)
    }

    @Test
    fun getTopRatedMovies() {

    }
}