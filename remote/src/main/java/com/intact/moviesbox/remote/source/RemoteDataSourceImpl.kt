package com.intact.moviesbox.remote.source

import com.intact.moviesbox.data.model.*
import com.intact.moviesbox.data.repository.RemoteDataSource
import com.intact.moviesbox.remote.api.MovieServiceRequests
import com.intact.moviesbox.remote.mapper.Mapper
import com.intact.moviesbox.remote.model.*
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

const val MOVIE_DB_API_KEY = "e254cf574a28681bc9e82ec1719360b5"

class RemoteDataSourceImpl @Inject constructor(
    private val movieServiceRequests: MovieServiceRequests,
    private val movieDataNetworkMapper: Mapper<MovieData, MovieDTONetwork>,
    private val popularDataNetworkMapper: Mapper<PopularMoviesData, PopularMoviesDTONetwork>,
    private val topRatedDataNetworkMapper: Mapper<TopRatedMoviesData, TopRatedMoviesDTONetwork>,
    private val trendingDataNetworkMapper: Mapper<TrendingMoviesData, TrendingMoviesDTONetwork>,
    private val upcomingDataNetworkMapper: Mapper<UpcomingMoviesData, UpcomingMoviesDTONetwork>,
    private val nowPlayingDataNetworkMapper: Mapper<NowPlayingMoviesData, NowPlayingMoviesDTONetwork>
) : RemoteDataSource {
    override fun getRunningNowMovies(pageNumber: String): Observable<NowPlayingMoviesData> {
        return movieServiceRequests.getNowPlayingMovies(
            apiKey = MOVIE_DB_API_KEY,
            pageNumber = pageNumber
        ).map { nowPlayingDataNetworkMapper.from(it) }
    }

    override fun getTopRatedMovies(pageNumber: String): Observable<TopRatedMoviesData> {
        return movieServiceRequests.getTopRatedMovies(
            apiKey = MOVIE_DB_API_KEY,
            pageNumber = pageNumber
        ).map { topRatedDataNetworkMapper.from(it) }
    }

    override fun getPopularMovies(pageNumber: String): Observable<PopularMoviesData> {
        return movieServiceRequests.getPopularMovies(
            apiKey = MOVIE_DB_API_KEY,
            pageNumber = pageNumber
        ).map { popularDataNetworkMapper.from(it) }
    }

    override fun getTrendingMovies(pageNumber: String): Observable<TrendingMoviesData> {
        return movieServiceRequests.getTrendingMovies(
            apiKey = MOVIE_DB_API_KEY,
            pageNumber = pageNumber
        ).map { trendingDataNetworkMapper.from(it) }
    }

    override fun getUpcomingMovies(pageNumber: String): Observable<UpcomingMoviesData> {
        return movieServiceRequests.getUpcomingMovies(
            apiKey = MOVIE_DB_API_KEY,
            pageNumber = pageNumber
        ).map { upcomingDataNetworkMapper.from(it) }
    }

    override fun getMovieDetails(movieId: Long): Single<MovieData> {
        return movieServiceRequests.getMovieDetails(
            movieId = movieId,
            apiKey = "e254cf574a28681bc9e82ec1719360b5"
        ).map { movieDataNetworkMapper.from(it) }
    }
}