package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.NowPlayingMoviesEntity
import com.intact.moviesbox.presentation.model.MovieDataResponseModel
import com.intact.moviesbox.presentation.model.MovieDetailModel

class MovieDataEntityMapper : Mapper<NowPlayingMoviesEntity, MovieDataResponseModel> {

    override fun from(e: MovieDataResponseModel): NowPlayingMoviesEntity {
        val movieList = ArrayList<MovieEntity>()
        val mapper = MovieDetailEntityMapper()

        for (movieModel in e.movies) {
            movieList.add(mapper.from(movieModel))
        }

        return NowPlayingMoviesEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = movieList
        )
    }

    override fun to(t: NowPlayingMoviesEntity): MovieDataResponseModel {
        val movieList = ArrayList<MovieDetailModel>()
        val mapper = MovieDetailEntityMapper()

        for (movieModel in t.movies) {
            movieList.add(mapper.to(movieModel))
        }
        return MovieDataResponseModel(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = movieList
        )
    }
}