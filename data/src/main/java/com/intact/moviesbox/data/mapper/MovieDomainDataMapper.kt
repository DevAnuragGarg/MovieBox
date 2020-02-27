package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.domain.entities.MovieEntity
import javax.inject.Inject

/**
 * mapper file to map the values from or to MovieEntity and MovieData
 */

class MovieDomainDataMapper @Inject constructor() : Mapper<MovieEntity, MovieData> {

    override fun from(e: MovieData): MovieEntity {
        return MovieEntity(
            id = e.id,
            runtime = e.runtime,
            budget = e.budget,
            title = e.title,
            revenue = e.revenue,
            adult = e.adult,
            status = e.status,
            imdbId = e.imdbId,
            tagLine = e.tagLine,
            voteCount = e.voteCount,
            overview = e.overview,
            popularity = e.popularity,
            posterPath = e.posterPath,
            voteAverage = e.voteAverage,
            releaseDate = e.releaseDate,
            backdropPath = e.backdropPath,
            originalTitle = e.originalTitle,
            originalLanguage = e.originalLanguage
        )
    }

    override fun to(t: MovieEntity): MovieData {
        return MovieData(
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