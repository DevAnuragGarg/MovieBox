package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.entities.TopRatedMoviesDomainDTO
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.model.TopRatedMoviesDTO
import javax.inject.Inject

/**
 * this mapper is used to convert TopRatedMoviesEntity to TopRatedMoviesDTO
 * or vice versa. Extending the base Mapper class.
 */
class TopRatedDomainPresentationMapper @Inject constructor() :
    Mapper<TopRatedMoviesDomainDTO, TopRatedMoviesDTO> {

    override fun from(e: TopRatedMoviesDTO): TopRatedMoviesDomainDTO {
        val movieList = ArrayList<MovieDomainDTO>()

        // using the movie entity mapper
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in e.movies) {
            movieList.add(mapper.from(movieModel))
        }

        return TopRatedMoviesDomainDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = movieList
        )
    }

    override fun to(t: TopRatedMoviesDomainDTO): TopRatedMoviesDTO {
        val movieList = ArrayList<MovieDTO>()
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in t.movies) {
            movieList.add(mapper.to(movieModel))
        }
        return TopRatedMoviesDTO(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = movieList
        )
    }
}