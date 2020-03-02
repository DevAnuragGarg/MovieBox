package com.intact.moviesbox.di.module

import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.NowPlayingMoviesEntity
import com.intact.moviesbox.domain.entities.TopRatedMoviesEntity
import com.intact.moviesbox.presentation.mapper.Mapper
import com.intact.moviesbox.presentation.mapper.MovieDomainPresentationMapper
import com.intact.moviesbox.presentation.mapper.NowPlayingDomainPresentationMapper
import com.intact.moviesbox.presentation.mapper.TopRatedDomainPresentationMapper
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.model.NowPlayingMoviesDTO
import com.intact.moviesbox.presentation.model.TopRatedMoviesDTO
import dagger.Binds
import dagger.Module

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindsMovieDomainPresentationMapper(movieDomainPresentationMapper: MovieDomainPresentationMapper): Mapper<MovieEntity, MovieDTO>

    @Binds
    abstract fun bindsNowPlayingDomainPresentationMapper(nowPlayingDomainPresentationMapper: NowPlayingDomainPresentationMapper): Mapper<NowPlayingMoviesEntity, NowPlayingMoviesDTO>

    @Binds
    abstract fun bindsTopRatedDomainPresentationMapper(topRatedDomainPresentationMapper: TopRatedDomainPresentationMapper): Mapper<TopRatedMoviesEntity, TopRatedMoviesDTO>
}