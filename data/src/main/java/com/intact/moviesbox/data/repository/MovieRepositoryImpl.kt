package com.intact.moviesbox.data.repository

import com.intact.moviesbox.data.mapper.*
import com.intact.moviesbox.domain.entities.*
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
    private val popularDomainDataMapper: PopularDomainDataMapper,
    private val topRatedDomainDataMapper: TopRatedDomainDataMapper,
    private val trendingDomainDataMapper: TrendingDomainDataMapper,
    private val upcomingDomainDataMapper: UpcomingDomainDataMapper,
    private val nowPlayingDomainDataMapper: NowPlayingDomainDataMapper
) : MovieRepository {

    override fun getPlayingNowMovies(pageNumber: String): Observable<NowPlayingMoviesEntity> {
        return remoteDataSource.getRunningNowMovies(pageNumber = pageNumber)
            .map { nowPlayingDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getUpcomingRatedMovies(pageNumber: String): Observable<UpcomingMoviesEntity> {
        return remoteDataSource.getUpcomingMovies(pageNumber = pageNumber)
            .map { upcomingDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getPopularMovies(pageNumber: String): Observable<PopularMoviesEntity> {
        return remoteDataSource.getPopularMovies(pageNumber = pageNumber)
            .map { popularDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getTopRatedMovies(pageNumber: String): Observable<TopRatedMoviesEntity> {
        return remoteDataSource.getTopRatedMovies(pageNumber = pageNumber)
            .map { topRatedDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getTrendingMovies(pageNumber: String): Observable<TrendingMoviesEntity> {
        return remoteDataSource.getTrendingMovies(pageNumber = pageNumber)
            .map { trendingDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getMovieDetails(movieId: Long): Single<MovieEntity> {
        // need to check for single
        return remoteDataSource.getMovieDetails(movieId = movieId)
            .map { movieDomainDataMapper.from(it) }.doOnError { }
    }
}