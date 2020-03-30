package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.UpcomingMoviesEntity
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.model.UpcomingMoviesDTO
import timber.log.Timber
import javax.inject.Inject

/**
 * this mapper is used to convert NowPlayingMoviesEntity to MovieDataResponseModel
 * or vice versa. Extending the base Mapper class.
 */
class UpcomingDomainPresentationMapper @Inject constructor() :
    Mapper<UpcomingMoviesEntity, UpcomingMoviesDTO> {

    override fun from(e: UpcomingMoviesDTO): UpcomingMoviesEntity {
        Timber.d(e.toString())
        val movieList = ArrayList<MovieEntity>()

        // using the movie entity mapper
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in e.movies) {
            movieList.add(mapper.from(movieModel))
        }

        return UpcomingMoviesEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = movieList
        )
    }

    override fun to(t: UpcomingMoviesEntity): UpcomingMoviesDTO {
        Timber.d(t.toString())
        val movieList = ArrayList<MovieDTO>()
        val mapper = MovieDomainPresentationMapper()

        for (movieModel in t.movies) {
            movieList.add(mapper.to(movieModel))
        }
        return UpcomingMoviesDTO(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = movieList
        )
    }
}