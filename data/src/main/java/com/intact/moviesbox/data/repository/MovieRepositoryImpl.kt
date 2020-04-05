package com.intact.moviesbox.data.repository

import com.intact.moviesbox.data.mapper.*
import com.intact.moviesbox.domain.entities.*
import com.intact.moviesbox.domain.repositories.MovieRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import javax.inject.Inject

/**
 * Repository @MovieRepository implementation. It will fetch the local and remote
 * source and get the data as per the requirement
 */
class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val movieDomainDataMapper: MovieDomainDataMapper,
    private val popularDomainDataMapper: PopularDomainDataMapper,
    private val topRatedDomainDataMapper: TopRatedDomainDataMapper,
    private val trendingDomainDataMapper: TrendingDomainDataMapper,
    private val upcomingDomainDataMapper: UpcomingDomainDataMapper,
    //private val movieListDomainDataMapper: MovieListDomainDataMapper,
    private val nowPlayingDomainDataMapper: NowPlayingDomainDataMapper
) : MovieRepository {

    override fun getPlayingNowMovies(pageNumber: String): Observable<NowPlayingMoviesDomainDTO> {
        return remoteDataSource.getRunningNowMovies(pageNumber = pageNumber)
            .map { nowPlayingDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getUpcomingRatedMovies(pageNumber: String): Observable<UpcomingMoviesDomainDTO> {
        return remoteDataSource.getUpcomingMovies(pageNumber = pageNumber)
            .map { upcomingDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getPopularMovies(pageNumber: String): Observable<PopularMoviesDomainDTO> {
        return remoteDataSource.getPopularMovies(pageNumber = pageNumber)
            .map { popularDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getTopRatedMovies(pageNumber: String): Observable<TopRatedMoviesDomainDTO> {
        return remoteDataSource.getTopRatedMovies(pageNumber = pageNumber)
            .map { topRatedDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getTrendingMovies(pageNumber: String): Observable<TrendingMoviesDomainDTO> {
        return remoteDataSource.getTrendingMovies(pageNumber = pageNumber)
            .map { trendingDomainDataMapper.from(it) }.onErrorResumeNext(Observable.empty())
    }

    override fun getMovieDetail(movieId: Long): Single<MovieDomainDTO> {
        // TODO: need to check for single on error
        return localDataSource.getMovieDetail(movieId = movieId)
            .map { movieDomainDataMapper.from(it) }.doOnError { }
    }

//    override fun saveAllMovies(movieDetailList: List<MovieDomainDTO>): Completable {
//        // TODO: need to check for completable on error
//        return localDataSource.saveMovieDetail(movieListDomainDataMapper.to(movieDetailList))
//            .doOnError { }
//    }

    override fun saveMovieDetail(movieDetail: MovieDomainDTO): Completable {
        // TODO: need to check for completable on error
        return localDataSource.saveMovieDetail(movieDomainDataMapper.to(movieDetail))
            .doOnError { }
    }
}