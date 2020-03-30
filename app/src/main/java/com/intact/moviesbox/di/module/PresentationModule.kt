package com.intact.moviesbox.di.module

import com.intact.moviesbox.domain.entities.*
import com.intact.moviesbox.presentation.mapper.*
import com.intact.moviesbox.presentation.model.*
import dagger.Binds
import dagger.Module

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindsMovieDomainPresentationMapper(movieDomainPresentationMapper: MovieDomainPresentationMapper): Mapper<MovieEntity, MovieDTO>

    @Binds
    abstract fun bindsPopularDomainPresentationMapper(popularDomainPresentationMapper: PopularDomainPresentationMapper): Mapper<PopularMoviesEntity, PopularMoviesDTO>

    @Binds
    abstract fun bindsTopRatedDomainPresentationMapper(topRatedDomainPresentationMapper: TopRatedDomainPresentationMapper): Mapper<TopRatedMoviesEntity, TopRatedMoviesDTO>

    @Binds
    abstract fun bindsTrendingDomainPresentationMapper(trendingDomainPresentationMapper: TrendingDomainPresentationMapper): Mapper<TrendingMoviesEntity, TrendingMoviesDTO>

    @Binds
    abstract fun bindsUpcomingDomainPresentationMapper(upcomingDomainPresentationMapper: UpcomingDomainPresentationMapper): Mapper<UpcomingMoviesEntity, UpcomingMoviesDTO>

    @Binds
    abstract fun bindsNowPlayingDomainPresentationMapper(nowPlayingDomainPresentationMapper: NowPlayingDomainPresentationMapper): Mapper<NowPlayingMoviesEntity, NowPlayingMoviesDTO>
}