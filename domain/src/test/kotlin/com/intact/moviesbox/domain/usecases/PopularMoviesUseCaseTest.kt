package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import com.intact.moviesbox.domain.util.TestDataGenerator
import io.reactivex.Observable
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
@RunWith(JUnit4::class) // add with @RunWith to mark it as jUnit test case
class PopularMoviesUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var baseSchedulerProvider: BaseSchedulerProvider

    private lateinit var popularMoviesUseCase: PopularMoviesUseCase

    /**
     * if any setup is required for the test it can be done @Before
     * this will prepare whatever is required before starting the tests
     */
    @Before
    fun setup() {
        // initializing mockito
        MockitoAnnotations.initMocks(this)

        // initializing use case
        popularMoviesUseCase =
            PopularMoviesUseCase(movieRepository, baseSchedulerProvider)
    }

    @Test
    fun testGetPopularMoviesSuccess() {
        // creating dummy data
        val popularMoviesData = TestDataGenerator.getPopularMoviesList()

        // whenever the get popular movies function is invoked just
        // return the Observable of the popular movies data
        Mockito.`when`(movieRepository.getPopularMovies("1"))
            .thenReturn(Observable.just(popularMoviesData))

        // rx also provide the test method that will return the observable created in the test cases
        val testObserver = popularMoviesUseCase.buildUseCase(PopularMoviesUseCase.Param("1")).test()

        // running that test observable using mockito
        Mockito.verify(movieRepository, Mockito.times(1)).getPopularMovies("1")

        // check if the dummy data received using mockito is same as passed
        testObserver.assertSubscribed()
            .assertValue { it == popularMoviesData }
            .assertComplete()
    }

    @Test
    fun testGetPopularMoviesFailure() {
        val errorMessage = "Error Occurred"

        // whenever the get popular movies functions invoked just
        // return the Error observable
        Mockito.`when`(movieRepository.getPopularMovies("1"))
            .thenReturn(Observable.error(Throwable(errorMessage)))

        // creating the test observable
        val testObservable =
            popularMoviesUseCase.buildUseCase(PopularMoviesUseCase.Param("1")).test()

        // verify get popular movies method of movie
        // repository with calling this method only one time
        Mockito.verify(movieRepository, Mockito.times(1)).getPopularMovies("1")

        // we are subscribing to the observable using assertSubscribed
        // and checking if we are getting error and comparing the error message
        // also checking that this observable is not giving a single value
        // using assertNotComplete
        testObservable.assertSubscribed().assertError { it.message?.equals(errorMessage) ?: false }
            .assertNotComplete()
    }
}