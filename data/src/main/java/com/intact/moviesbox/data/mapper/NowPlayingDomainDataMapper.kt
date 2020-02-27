package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.NowPlayingMoviesData
import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.NowPlayingMoviesEntity
import javax.inject.Inject

class NowPlayingDomainDataMapper @Inject constructor() :
    Mapper<NowPlayingMoviesEntity, NowPlayingMoviesData> {

    override fun from(e: NowPlayingMoviesData): NowPlayingMoviesEntity {
        val nowPlayingMovies = ArrayList<MovieEntity>()

        // used the movie domain data mapper to convert
        // movie data into movie entity
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in e.movies) {
            nowPlayingMovies.add(movieDomainDataMapper.from(movie))
        }

        return NowPlayingMoviesEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = nowPlayingMovies
        )
    }

    override fun to(t: NowPlayingMoviesEntity): NowPlayingMoviesData {
        val nowPlayingMovies = ArrayList<MovieData>()

        // used the movie domain data mapper to convert
        // movie entity into movie data
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in t.movies) {
            nowPlayingMovies.add(movieDomainDataMapper.to(movie))
        }

        return NowPlayingMoviesData(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = nowPlayingMovies)
    }
}