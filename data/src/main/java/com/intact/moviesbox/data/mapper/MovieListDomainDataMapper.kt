package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.domain.entities.MovieDomainDTO
import javax.inject.Inject

class MovieListDomainDataMapper @Inject constructor() :
    Mapper<List<MovieDomainDTO>, List<MovieDataDTO>> {

    override fun from(e: List<MovieDataDTO>): List<MovieDomainDTO> {
        val movieDomainDTOList = ArrayList<MovieDomainDTO>()
        val mapper = MovieDomainDataMapper()

        for (movieDataDTO in e) {
            movieDomainDTOList.add(mapper.from(movieDataDTO))
        }
        return movieDomainDTOList
    }

    override fun to(t: List<MovieDomainDTO>): List<MovieDataDTO> {
        val movieDataDTOList = ArrayList<MovieDataDTO>()
        val mapper = MovieDomainDataMapper()

        for (movieDomainDTO in t) {
            movieDataDTOList.add(mapper.to(movieDomainDTO))
        }
        return movieDataDTOList
    }
}