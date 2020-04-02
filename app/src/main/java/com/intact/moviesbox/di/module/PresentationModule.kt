package com.intact.moviesbox.di.module

import com.intact.moviesbox.domain.entities.*
import com.intact.moviesbox.presentation.mapper.*
import com.intact.moviesbox.presentation.model.*
import dagger.Binds
import dagger.Module

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindsMovieDomainPresentationMapper(movieDomainPresentationMapper: MovieDomainPresentationMapper): Mapper<MovieDomainDTO, MovieDTO>

    @Binds
    abstract fun bindsPopularDomainPresentationMapper(popularDomainPresentationMapper: PopularDomainPresentationMapper): Mapper<PopularMoviesDomainDTO, PopularMoviesDTO>

    @Binds
    abstract fun bindsTopRatedDomainPresentationMapper(topRatedDomainPresentationMapper: TopRatedDomainPresentationMapper): Mapper<TopRatedMoviesDomainDTO, TopRatedMoviesDTO>

    @Binds
    abstract fun bindsTrendingDomainPresentationMapper(trendingDomainPresentationMapper: TrendingDomainPresentationMapper): Mapper<TrendingMoviesDomainDTO, TrendingMoviesDTO>

    @Binds
    abstract fun bindsUpcomingDomainPresentationMapper(upcomingDomainPresentationMapper: UpcomingDomainPresentationMapper): Mapper<UpcomingMoviesDomainDTO, UpcomingMoviesDTO>

    @Binds
    abstract fun bindsNowPlayingDomainPresentationMapper(nowPlayingDomainPresentationMapper: NowPlayingDomainPresentationMapper): Mapper<NowPlayingMoviesDomainDTO, NowPlayingMoviesDTO>
}