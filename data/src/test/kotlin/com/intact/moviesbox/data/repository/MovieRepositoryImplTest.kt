package com.intact.moviesbox.data.repository

import com.intact.moviesbox.data.mapper.MovieDomainDataMapper
import com.intact.moviesbox.data.mapper.NowPlayingDomainDataMapper
import com.intact.moviesbox.domain.repositories.MovieRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MovieRepositoryImplTest {

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var movieRepository: MovieRepository
    private val movieDomainDataMapper = MovieDomainDataMapper()
    private val nowPlayingDomainDataMapper = NowPlayingDomainDataMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieRepository = MovieRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            movieDomainDataMapper = movieDomainDataMapper,
            nowPlayingDomainDataMapper = nowPlayingDomainDataMapper
        )
    }

    @Test
    fun testGetNowPlayingRemoteSuccess() {

    }
}