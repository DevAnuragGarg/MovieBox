package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.NowPlayingMoviesEntity
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.model.NowPlayingMoviesDTO
import javax.inject.Inject

/**
 * this mapper is used to convert NowPlayingMoviesEntity to MovieDataResponseModel
 * or vice versa. Extending the base Mapper class.
 */
class NowPlayingDomainPresentationMapper @Inject constructor() :
    Mapper<NowPlayingMoviesEntity, NowPlayingMoviesDTO> {

    override fun from(e: NowPlayingMoviesDTO): NowPlayingMoviesEntity {
        val movieList = ArrayList<MovieEntity>()

        // using the movie entity mapper
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in e.movies) {
            movieList.add(mapper.from(movieModel))
        }

        return NowPlayingMoviesEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = movieList
        )
    }

    override fun to(t: NowPlayingMoviesEntity): NowPlayingMoviesDTO {
        val movieList = ArrayList<MovieDTO>()
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in t.movies) {
            movieList.add(mapper.to(movieModel))
        }
        return NowPlayingMoviesDTO(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = movieList
        )
    }
}