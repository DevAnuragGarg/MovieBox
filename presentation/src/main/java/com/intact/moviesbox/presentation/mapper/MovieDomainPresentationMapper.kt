package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.presentation.model.MovieDTO
import javax.inject.Inject

/**
 * mapper class to convert the movie domain layer data to movie presentation layer data
 */
class MovieDomainPresentationMapper @Inject constructor(): Mapper<MovieDomainDTO, MovieDTO> {

    override fun from(e: MovieDTO): MovieDomainDTO {
        return MovieDomainDTO(
            voteCount = e.voteCount,
            id = e.id,
            voteAverage = e.voteAverage,
            title = e.title,
            popularity = e.popularity,
            posterPath = e.posterPath,
            originalLanguage = e.originalLanguage,
            originalTitle = e.originalTitle,
            backdropPath = e.backdropPath,
            adult = e.adult,
            overview = e.overview,
            releaseDate = e.releaseDate,
            tagLine = e.tagLine,
            budget = e.budget,
            imdbId = e.imdbId,
            revenue = e.revenue,
            runtime = e.runtime,
            status = e.status
        )
    }

    override fun to(t: MovieDomainDTO): MovieDTO {
        return MovieDTO(
            id = t.id,
            title = t.title,
            adult = t.adult,
            budget = t.budget,
            status = t.status,
            imdbId = t.imdbId,
            runtime = t.runtime,
            revenue = t.revenue,
            tagLine = t.tagLine,
            overview = t.overview,
            voteCount = t.voteCount,
            popularity = t.popularity,
            posterPath = t.posterPath,
            voteAverage = t.voteAverage,
            releaseDate = t.releaseDate,
            backdropPath = t.backdropPath,
            originalTitle = t.originalTitle,
            originalLanguage = t.originalLanguage
        )
    }
}