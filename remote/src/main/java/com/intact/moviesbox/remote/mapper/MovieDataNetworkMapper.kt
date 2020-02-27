package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieDTONetwork
import com.intact.moviesbox.data.model.MovieData

class MovieDataNetworkMapper : Mapper<MovieData, MovieDTONetwork> {
    override fun from(e: MovieDTONetwork): MovieData {
        return MovieData(
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