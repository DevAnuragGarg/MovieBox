package com.intact.filmireview.domain.usecases

import com.intact.filmireview.domain.repositories.BaseMovieRepository
import com.intact.filmireview.domain.util.TestDataGenerator
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Trampoline: This scheduler runs the code on current thread. So if
 * you have a code running on the main thread, this scheduler will add
 * the code block on the queue of main thread
 */
@RunWith(JUnit4::class)
class GetPopularMoviesTest {

    @Mock
    private lateinit var baseMovieRepository: BaseMovieRepository

    private lateinit var popularMoviesUseCase: PopularMoviesUseCase

    //setting up mockito
    @Before
    fun setup() {
        // initializing mockito
        MockitoAnnotations.initMocks(this)

        // initializing use case
        popularMoviesUseCase =
            PopularMoviesUseCase(baseMovieRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun testGetPopularMoviesSuccess() {
        // creating dummy data
        val popularMoviesData = TestDataGenerator.getPopularMoviesList()

        // if the request is sent, dummy response to be returned
        Mockito.`when`(baseMovieRepository.getPopularMovies("1"))
            .thenReturn(Single.just(popularMoviesData))

        // creating a use case test observable
        val testObserver = popularMoviesUseCase.buildUseCase(PopularMoviesUseCase.Param("1")).test()

        // running that test observable using mockito
        Mockito.verify(baseMovieRepository, Mockito.times(1))
            .getPopularMovies("1")

        // check if the dummy data received using mockito is same as passed
        testObserver.assertSubscribed()
            .assertValue { it == popularMoviesData }
            .assertComplete()
    }
}