package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieDetailEntity
import com.intact.moviesbox.presentation.model.MovieDetailModel

/**
 * helper class to convert the domain layer data to presentation layer data
 */

class MovieDetailEntityMapper : Mapper<MovieDetailEntity, MovieDetailModel> {

    override fun from(e: MovieDetailModel): MovieDetailEntity {
        return MovieDetailEntity(
            voteCount = e.voteCount,
            id = e.id,
            video = e.video,
            voteAverage = e.voteAverage,
            title = e.title,
            popularity = e.popularity,
            posterPath = e.posterPath,
            originalLanguage = e.originalLanguage,
            originalTitle = e.originalTitle,
            backdropPath = e.backdropPath,
            adult = e.adult,
            overview = e.overview,
            releaseDate = e.releaseDate
        )
    }

    override fun to(t: MovieDetailEntity): MovieDetailModel {
        return MovieDetailModel(
            voteCount = t.voteCount,
            id = t.id,
            video = t.video,
            voteAverage = t.voteAverage,
            title = t.title,
            popularity = t.popularity,
            posterPath = t.posterPath,
            originalLanguage = t.originalLanguage,
            originalTitle = t.originalTitle,
            backdropPath = t.backdropPath,
            adult = t.adult,
            overview = t.overview,
            releaseDate = t.releaseDate
        )
    }
}