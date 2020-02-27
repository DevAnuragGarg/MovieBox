package com.intact.moviesbox.remote.source

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.NowPlayingMoviesData
import com.intact.moviesbox.data.repository.RemoteDataSource
import com.intact.moviesbox.remote.api.MovieServiceRequests
import com.intact.moviesbox.remote.mapper.Mapper
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.NowPlayingMoviesDTONetwork
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieServiceRequests: MovieServiceRequests,
    private val movieDataNetworkMapper: Mapper<MovieData, MovieDTONetwork>,
    private val nowPlayingDataNetworkMapper: Mapper<NowPlayingMoviesData, NowPlayingMoviesDTONetwork>
) : RemoteDataSource {

    override fun getRunningNowMovies(pageNumber: String): Observable<NowPlayingMoviesData> {
        return movieServiceRequests.getNowPlayingMovies(
            apiKey = "e254cf574a28681bc9e82ec1719360b5",
            pageNumber = pageNumber
        )
            .map { nowPlayingDataNetworkMapper.from(it) }
    }

    override fun getTopRatedMovies(pageNumber: String): Observable<NowPlayingMoviesData> {
        return movieServiceRequests.getTopRatedMovies(
            apiKey = "e254cf574a28681bc9e82ec1719360b5",
            pageNumber = pageNumber
        )
            .map { nowPlayingDataNetworkMapper.from(it) }
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
        return movieServiceRequests.getMovieDetails(
            movieId = movieId,
            apiKey = "e254cf574a28681bc9e82ec1719360b5"
        )
            .map { movieDataNetworkMapper.from(it) }
    }
}