package com.intact.moviesbox.remote.source

import com.intact.moviesbox.data.model.MovieDTONetwork
import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.NowPlayingMoviesDTONetwork
import com.intact.moviesbox.data.model.NowPlayingMoviesData
import com.intact.moviesbox.data.repository.RemoteDataSource
import com.intact.moviesbox.remote.api.MovieServiceRequests
import com.intact.moviesbox.remote.mapper.Mapper
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieServiceRequests: MovieServiceRequests,
    private val movieDataNetworkMapper: Mapper<MovieData, MovieDTONetwork>,
    private val nowPlayingDataNetworkMapper: Mapper<NowPlayingMoviesData, NowPlayingMoviesDTONetwork>
) : RemoteDataSource {

    override fun getRunningNowMovies(pageNumber: String): Observable<NowPlayingMoviesData> {
        return movieServiceRequests.getNowPlayingMovies(apiKey = "", pageNumber = pageNumber)
            .map { nowPlayingDataNetworkMapper.from(it) }
    }

    override fun getTopRatedMovies(pageNumber: String): Observable<NowPlayingMoviesData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUpcomingMovies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPopularMovies(pageNumber: String): Observable<NowPlayingMoviesData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrendingMovies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovieDetails(movieId: Long): Single<MovieData> {
        return movieServiceRequests.getMovieDetails(movieId = movieId, apiKey = "")
            .map { movieDataNetworkMapper.from(it) }
    }
}