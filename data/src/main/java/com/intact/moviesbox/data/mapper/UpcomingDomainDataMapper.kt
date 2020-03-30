package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.UpcomingMoviesData
import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.UpcomingMoviesEntity
import javax.inject.Inject

class UpcomingDomainDataMapper @Inject constructor() :
    Mapper<UpcomingMoviesEntity, UpcomingMoviesData> {

    override fun from(e: UpcomingMoviesData): UpcomingMoviesEntity {
        val upcomingMovies = ArrayList<MovieEntity>()

        // used the movie domain data mapper to convert
        // movie data into movie entity
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in e.movies) {
            upcomingMovies.add(movieDomainDataMapper.from(movie))
        }

        return UpcomingMoviesEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = upcomingMovies
        )
    }

    override fun to(t: UpcomingMoviesEntity): UpcomingMoviesData {
        val upcomingMovies = ArrayList<MovieData>()

        // used the movie domain data mapper to convert
        // movie entity into movie data
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in t.movies) {
            upcomingMovies.add(movieDomainDataMapper.to(movie))
        }

        return UpcomingMoviesData(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = upcomingMovies
        )
    }
}