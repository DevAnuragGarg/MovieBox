package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.remote.model.MovieDTONetwork
import javax.inject.Inject

class MovieDataNetworkMapper @Inject constructor() : Mapper<MovieData, MovieDTONetwork> {
    override fun from(e: MovieDTONetwork): MovieData {
        return MovieData(
            id = e.id,
            title = e.title,
            adult = e.adult,
            budget = e.budget,
            imdbId = e.imdbId,
            status = e.status,
            tagLine = e.tagLine,
            revenue = e.revenue,
            runtime = e.runtime,
            overview = e.overview,
            voteCount = e.voteCount,
            posterPath = e.posterPath,
            popularity = e.popularity,
            voteAverage = e.voteAverage,
            releaseDate = e.releaseDate,
            backdropPath = e.backdropPath,
            originalTitle = e.originalTitle,
            originalLanguage = e.originalLanguage
        )
    }

    override fun to(t: MovieData): MovieDTONetwork {
        return MovieDTONetwork(
            voteCount = t.voteCount,
            id = t.id,
            voteAverage = t.voteAverage,
            title = t.title,
            popularity = t.popularity,
            posterPath = t.posterPath,
            originalLanguage = t.originalLanguage,
            originalTitle = t.originalTitle,
            backdropPath = t.backdropPath,
            adult = t.adult,
            overview = t.overview,
            releaseDate = t.releaseDate,
            tagLine = t.tagLine,
            budget = t.budget,
            imdbId = t.imdbId,
            revenue = t.revenue,
            runtime = t.runtime,
            status = t.status
        )
    }
}