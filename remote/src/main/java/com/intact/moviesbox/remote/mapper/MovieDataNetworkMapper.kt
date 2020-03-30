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
            id = t.id,
            title = t.title,
            adult = t.adult,
            budget = t.budget,
            status = t.status,
            imdbId = t.imdbId,
            tagLine = t.tagLine,
            revenue = t.revenue,
            runtime = t.runtime,
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