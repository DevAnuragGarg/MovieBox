package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.TopRatedMoviesData
import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.TopRatedMoviesEntity
import javax.inject.Inject

class TopRatedDomainDataMapper @Inject constructor() :
    Mapper<TopRatedMoviesEntity, TopRatedMoviesData> {

    override fun from(e: TopRatedMoviesData): TopRatedMoviesEntity {
        val topRatedMovies = ArrayList<MovieEntity>()

        // used the movie domain data mapper to convert
        // movie data into movie entity
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in e.movies) {
            topRatedMovies.add(movieDomainDataMapper.from(movie))
        }

        return TopRatedMoviesEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = topRatedMovies
        )
    }

    override fun to(t: TopRatedMoviesEntity): TopRatedMoviesData {
        val topRatedMovies = ArrayList<MovieData>()

        // used the movie domain data mapper to convert
        // movie entity into movie data
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in t.movies) {
            topRatedMovies.add(movieDomainDataMapper.to(movie))
        }

        return TopRatedMoviesData(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = topRatedMovies
        )
    }
}