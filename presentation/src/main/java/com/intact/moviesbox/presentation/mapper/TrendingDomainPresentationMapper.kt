package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.entities.TrendingMoviesDomainDTO
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.model.TrendingMoviesDTO
import javax.inject.Inject

/**
 * this mapper is used to convert TopRatedMoviesEntity to TopRatedMoviesDTO
 * or vice versa. Extending the base Mapper class.
 */
class TrendingDomainPresentationMapper @Inject constructor() :
    Mapper<TrendingMoviesDomainDTO, TrendingMoviesDTO> {

    override fun from(e: TrendingMoviesDTO): TrendingMoviesDomainDTO {
        val movieList = ArrayList<MovieDomainDTO>()

        // using the movie entity mapper
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in e.movies) {
            movieList.add(mapper.from(movieModel))
        }

        return TrendingMoviesDomainDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = movieList
        )
    }

    override fun to(t: TrendingMoviesDomainDTO): TrendingMoviesDTO {
        val movieList = ArrayList<MovieDTO>()
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in t.movies) {
            movieList.add(mapper.to(movieModel))
        }
        return TrendingMoviesDTO(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = movieList
        )
    }
}