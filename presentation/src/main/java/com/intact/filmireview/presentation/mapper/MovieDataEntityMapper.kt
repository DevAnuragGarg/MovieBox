package com.intact.filmireview.presentation.mapper

import com.intact.filmireview.domain.entities.MovieDetailEntity
import com.intact.filmireview.domain.entities.MovieResponseEntity
import com.intact.filmireview.presentation.model.MovieDataResponseModel
import com.intact.filmireview.presentation.model.MovieDetailModel

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