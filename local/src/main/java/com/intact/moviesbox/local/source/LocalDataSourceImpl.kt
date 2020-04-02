package com.intact.moviesbox.local.source

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.repository.LocalDataSource
import com.intact.moviesbox.local.database.MovieDAO
import com.intact.moviesbox.local.entity.MovieEntity
import com.intact.moviesbox.local.mapper.Mapper
import com.intact.moviesbox.local.mapper.MovieLocalDataMapper
import com.intact.moviesbox.local.mapper.MoviesListLocalDataMapper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    val movieDAO: MovieDAO,
    val movieLocalDataMapper: Mapper<MovieEntity, MovieDataDTO>
    //val moviesListLocalDataMapper: Mapper<List<MovieEntity>, List<MovieDataDTO>>
) : LocalDataSource {

    override fun getMovieDetail(movieId: Long): Single<MovieDataDTO> {
        return movieDAO.getMovieDetail(movieId).map { movieLocalDataMapper.to(it) }
    }

    override fun saveMovieDetail(movieDataDTO: MovieDataDTO): Completable {
        return movieDAO.saveMovie(movieLocalDataMapper.from(movieDataDTO))
    }

//    override fun saveMovieDetails(movieDataDTO: List<MovieDataDTO>): Completable {
//        return movieDAO.saveMovie(moviesListLocalDataMapper.from(movieDataDTO))
//    }
}