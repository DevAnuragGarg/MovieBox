package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.TopRatedMoviesEntity
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.model.TopRatedMoviesDTO
import javax.inject.Inject

/**
 * this mapper is used to convert TopRatedMoviesEntity to TopRatedMoviesDTO
 * or vice versa. Extending the base Mapper class.
 */
class TopRatedDomainPresentationMapper @Inject constructor() :
    Mapper<TopRatedMoviesEntity, TopRatedMoviesDTO> {

    override fun from(e: TopRatedMoviesDTO): TopRatedMoviesEntity {
        val movieList = ArrayList<MovieEntity>()

        // using the movie entity mapper
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in e.movies) {
            movieList.add(mapper.from(movieModel))
        }

        return TopRatedMoviesEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = movieList
        )
    }

    override fun to(t: TopRatedMoviesEntity): TopRatedMoviesDTO {
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