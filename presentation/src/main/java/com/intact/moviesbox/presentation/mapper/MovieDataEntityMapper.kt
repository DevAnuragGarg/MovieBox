package com.intact.moviesbox.presentation.mapper

import com.intact.moviesbox.domain.entities.MovieDetailEntity
import com.intact.moviesbox.domain.entities.MovieResponseEntity
import com.intact.moviesbox.presentation.model.MovieDataResponseModel
import com.intact.moviesbox.presentation.model.MovieDetailModel

class MovieDataEntityMapper : Mapper<MovieResponseEntity, MovieDataResponseModel> {

    override fun from(e: MovieDataResponseModel): MovieResponseEntity {
        val movieList = ArrayList<MovieDetailEntity>()
        val mapper = MovieDetailEntityMapper()

        for (movieModel in e.movies) {
            movieList.add(mapper.from(movieModel))
        }

        return MovieResponseEntity(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = movieList
        )
    }

    override fun to(t: MovieResponseEntity): MovieDataResponseModel {
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