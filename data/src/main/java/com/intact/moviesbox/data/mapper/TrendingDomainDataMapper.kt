package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.TrendingMoviesData
import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.TrendingMoviesEntity
import javax.inject.Inject

class TrendingDomainDataMapper @Inject constructor() :
    Mapper<TrendingMoviesEntity, TrendingMoviesData> {

    override fun from(e: TrendingMoviesData): TrendingMoviesEntity {
        val trendingMovies = ArrayList<MovieEntity>()

        // used the movie domain data mapper to convert
        // movie data into movie entity
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in e.movies) {
            trendingMovies.add(movieDomainDataMapper.from(movie))
        }

        return TrendingMoviesEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = trendingMovies
        )
    }

    override fun to(t: TrendingMoviesEntity): TrendingMoviesData {
        val trendingMovies = ArrayList<MovieData>()

        // used the movie domain data mapper to convert
        // movie entity into movie data
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in t.movies) {
            trendingMovies.add(movieDomainDataMapper.to(movie))
        }

        return TrendingMoviesData(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = trendingMovies
        )
    }
}