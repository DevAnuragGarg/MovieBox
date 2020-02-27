package com.intact.moviesbox.data.repository

import com.intact.moviesbox.data.mapper.MovieDomainDataMapper
import com.intact.moviesbox.data.mapper.NowPlayingDomainDataMapper
import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.NowPlayingMoviesEntity
import com.intact.moviesbox.domain.repositories.MovieRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Repository @MovieRepository implementation. It will fetch the local and remote
 * source and get the data as per the requirement
 */
class MovieRepositoryImpl @Inject constructor(
//    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val movieDomainDataMapper: MovieDomainDataMapper,
    private val nowPlayingDomainDataMapper: NowPlayingDomainDataMapper
) : MovieRepository {

    override fun getPlayingNowMovies(pageNumber: String): Observable<NowPlayingMoviesEntity> {
        return remoteDataSource.getRunningNowMovies(pageNumber = pageNumber)
            .map { nowPlayingDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getPopularMovies(pageNumber: String): Observable<NowPlayingMoviesEntity> {
        return remoteDataSource.getPopularMovies(pageNumber = pageNumber)
            .map { nowPlayingDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getTopRatedMovies(pageNumber: String): Observable<NowPlayingMoviesEntity> {
        return remoteDataSource.getTopRatedMovies(pageNumber = pageNumber)
            .map { nowPlayingDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getMovieDetails(movieId: Long): Single<MovieEntity> {
        // need to check for single
        return remoteDataSource.getMovieDetails(movieId = movieId)
            .map { movieDomainDataMapper.from(it) }.doOnError { }
    }
}