package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.entities.NowPlayingMoviesDomainDTO
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.model.NowPlayingMoviesDTO
import javax.inject.Inject

/**
 * this mapper is used to convert NowPlayingMoviesEntity to MovieDataResponseModel
 * or vice versa. Extending the base Mapper class.
 */
class NowPlayingDomainPresentationMapper @Inject constructor() :
    Mapper<NowPlayingMoviesDomainDTO, NowPlayingMoviesDTO> {

    override fun from(e: NowPlayingMoviesDTO): NowPlayingMoviesDomainDTO {
        val movieList = ArrayList<MovieDomainDTO>()

        // using the movie entity mapper
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in e.movies) {
            movieList.add(mapper.from(movieModel))
        }

        return NowPlayingMoviesDomainDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = movieList
        )
    }

    override fun to(t: NowPlayingMoviesDomainDTO): NowPlayingMoviesDTO {
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