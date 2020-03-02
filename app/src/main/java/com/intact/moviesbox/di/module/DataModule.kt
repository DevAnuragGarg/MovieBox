package com.intact.moviesbox.di.module

import com.intact.moviesbox.data.mapper.Mapper
import com.intact.moviesbox.data.mapper.MovieDomainDataMapper
import com.intact.moviesbox.data.mapper.NowPlayingDomainDataMapper
import com.intact.moviesbox.data.mapper.TopRatedDomainDataMapper
import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.NowPlayingMoviesData
import com.intact.moviesbox.data.model.TopRatedMoviesData
import com.intact.moviesbox.data.repository.MovieRepositoryImpl
import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.NowPlayingMoviesEntity
import com.intact.moviesbox.domain.entities.TopRatedMoviesEntity
import com.intact.moviesbox.domain.repositories.MovieRepository
import dagger.Binds
import dagger.Module

/**
 * dagger module having the dependencies listed here
 */
@Module
abstract class DataModule {

    @Binds
    abstract fun bindsRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindsMovieDomainDataMapper(movieDomainDataMapper: MovieDomainDataMapper): Mapper<MovieEntity, MovieData>

    @Binds
    abstract fun bindsNowPlayingDomainDataMapper(nowPlayingDomainDataMapper: NowPlayingDomainDataMapper): Mapper<NowPlayingMoviesEntity, NowPlayingMoviesData>

    @Binds
    abstract fun bindsTopRatedDomainDataMapper(topRatedDomainDataMapper: TopRatedDomainDataMapper): Mapper<TopRatedMoviesEntity, TopRatedMoviesData>
}