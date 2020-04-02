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
    private val movieDataDTONetworkMapper: Mapper<MovieDataDTO, MovieDTONetwork>,
    private val popularDataDTONetworkMapper: Mapper<PopularMoviesDataDTO, PopularMoviesDTONetwork>,
    private val topRatedDataDTONetworkMapper: Mapper<TopRatedMoviesDataDTO, TopRatedMoviesDTONetwork>,
    private val trendingDataDTONetworkMapper: Mapper<TrendingMoviesDataDTO, TrendingMoviesDTONetwork>,
    private val upcomingDataDTONetworkMapper: Mapper<UpcomingMoviesDataDTO, UpcomingMoviesDTONetwork>,
    private val nowPlayingDataDTONetworkMapper: Mapper<NowPlayingMoviesDataDTO, NowPlayingMoviesDTONetwork>
) : RemoteDataSource {
    override fun getRunningNowMovies(pageNumber: String): Observable<NowPlayingMoviesDataDTO> {
        return movieServiceRequests.getNowPlayingMovies(
            apiKey = MOVIE_DB_API_KEY,
            pageNumber = pageNumber
        ).map { nowPlayingDataDTONetworkMapper.from(it) }
    }

    override fun getTopRatedMovies(pageNumber: String): Observable<TopRatedMoviesDataDTO> {
        return movieServiceRequests.getTopRatedMovies(
            apiKey = MOVIE_DB_API_KEY,
            pageNumber = pageNumber
        ).map { topRatedDataDTONetworkMapper.from(it) }
    }

    override fun getPopularMovies(pageNumber: String): Observable<PopularMoviesDataDTO> {
        return movieServiceRequests.getPopularMovies(
            apiKey = MOVIE_DB_API_KEY,
            pageNumber = pageNumber
        ).map { popularDataDTONetworkMapper.from(it) }
    }

    override fun getTrendingMovies(pageNumber: String): Observable<TrendingMoviesDataDTO> {
        return movieServiceRequests.getTrendingMovies(
            apiKey = MOVIE_DB_API_KEY,
            pageNumber = pageNumber
        ).map { trendingDataDTONetworkMapper.from(it) }
    }

    override fun getUpcomingMovies(pageNumber: String): Observable<UpcomingMoviesDataDTO> {
        return movieServiceRequests.getUpcomingMovies(
            apiKey = MOVIE_DB_API_KEY,
            pageNumber = pageNumber
        ).map { upcomingDataDTONetworkMapper.from(it) }
    }

    override fun getMovieDetails(movieId: Long): Single<MovieDataDTO> {
        return movieServiceRequests.getMovieDetails(
            movieId = movieId,
            apiKey = MOVIE_DB_API_KEY
        ).map { movieDataDTONetworkMapper.from(it) }
    }
}