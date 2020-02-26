package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.repositories.MovieRepository
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class TopRatedMoviesUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var topRatedMoviesUseCase: TopRatedMoviesUseCase

    /**
     * if any setup is required for the test it can be done @Before
     * this will prepare whatever is required before starting the tests
     */
    @Before
    fun setUp() {
        // initializing mockito
        MockitoAnnotations.initMocks(this)

        // initializing use case
        topRatedMoviesUseCase =
            TopRatedMoviesUseCase(movieRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun getTopRatedMovies() {

    }
}