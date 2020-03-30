package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.PopularMoviesData
import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.PopularMoviesEntity
import javax.inject.Inject

class PopularDomainDataMapper @Inject constructor() :
    Mapper<PopularMoviesEntity, PopularMoviesData> {

    override fun from(e: PopularMoviesData): PopularMoviesEntity {
        val popularMovies = ArrayList<MovieEntity>()

        // used the movie domain data mapper to convert
        // movie data into movie entity
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in e.movies) {
            popularMovies.add(movieDomainDataMapper.from(movie))
        }

        return PopularMoviesEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = popularMovies
        )
    }

    override fun to(t: PopularMoviesEntity): PopularMoviesData {
        val popularMovies = ArrayList<MovieData>()

        // used the movie domain data mapper to convert
        // movie entity into movie data
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in t.movies) {
            popularMovies.add(movieDomainDataMapper.to(movie))
        }

        return PopularMoviesData(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = popularMovies
        )
    }
}