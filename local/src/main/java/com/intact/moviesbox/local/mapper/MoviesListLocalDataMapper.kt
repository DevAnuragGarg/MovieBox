package com.intact.moviesbox.local.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.local.entity.MovieEntity

class MoviesListLocalDataMapper :
    Mapper<List<MovieEntity>, List<MovieDataDTO>> {
    override fun from(e: List<MovieDataDTO>): List<MovieEntity> {
        val movieEntityList = ArrayList<MovieEntity>()
        val mapper = MovieLocalDataMapper()

        for (movieDataDTO in e) {
            movieEntityList.add(mapper.from(movieDataDTO))
        }
        return movieEntityList
    }

    override fun to(t: List<MovieEntity>): List<MovieDataDTO> {
        val movieDataList = ArrayList<MovieDataDTO>()
        val mapper = MovieLocalDataMapper()

        for (movieEntity in t) {
            movieDataList.add(mapper.to(movieEntity))
        }
        return movieDataList
    }
}