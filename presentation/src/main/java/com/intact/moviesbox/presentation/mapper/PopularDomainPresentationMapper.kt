package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.PopularMoviesEntity
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.model.PopularMoviesDTO
import javax.inject.Inject

/**
 * this mapper is used to convert TopRatedMoviesEntity to TopRatedMoviesDTO
 * or vice versa. Extending the base Mapper class.
 */
class PopularDomainPresentationMapper @Inject constructor() :
    Mapper<PopularMoviesEntity, PopularMoviesDTO> {

    override fun from(e: PopularMoviesDTO): PopularMoviesEntity {
        val movieList = ArrayList<MovieEntity>()

        // using the movie entity mapper
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in e.movies) {
            movieList.add(mapper.from(movieModel))
        }

        return PopularMoviesEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = movieList
        )
    }

    override fun to(t: PopularMoviesEntity): PopularMoviesDTO {
        val movieList = ArrayList<MovieDTO>()
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in t.movies) {
            movieList.add(mapper.to(movieModel))
        }
        return PopularMoviesDTO(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = movieList
        )
    }
}