package com.intact.moviesbox.di.module

import com.intact.moviesbox.data.model.*
import com.intact.moviesbox.data.repository.RemoteDataSource
import com.intact.moviesbox.remote.api.MovieServiceRequests
import com.intact.moviesbox.remote.mapper.*
import com.intact.moviesbox.remote.model.*
import com.intact.moviesbox.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, RemoteRequestModule.Binders::class])
class RemoteRequestModule {

    @Provides
    @Singleton
    internal fun providesMovieServiceRequests(retrofit: Retrofit) =
        retrofit.create(MovieServiceRequests::class.java)

    // another interface to bind the remote module related
    // mapper classes and remote data source
    @Module
    interface Binders {

        @Binds
        fun bindsRemoteSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

        @Binds
        fun bindMovieDataNetworkMapper(movieDataNetworkMapper: MovieDataNetworkMapper): Mapper<MovieData, MovieDTONetwork>

        @Binds
        fun bindPopularDataNetworkMapper(popularDataNetworkMapper: PopularDataNetworkMapper): Mapper<PopularMoviesData, PopularMoviesDTONetwork>

        @Binds
        fun bindTopRatedDataNetworkMapper(topRatedDataNetworkMapper: TopRatedDataNetworkMapper): Mapper<TopRatedMoviesData, TopRatedMoviesDTONetwork>

        @Binds
        fun bindUpcomingDataNetworkMapper(upcomingDataNetworkMapper: UpcomingDataNetworkMapper): Mapper<UpcomingMoviesData, UpcomingMoviesDTONetwork>

        @Binds
        fun bindTrendingDataNetworkMapper(trendingDataNetworkMapper: TrendingDataNetworkMapper): Mapper<TrendingMoviesData, TrendingMoviesDTONetwork>

        @Binds
        fun bindNowPlayingDataNetworkMapper(nowPlayingDataNetworkMapper: NowPlayingDataNetworkMapper): Mapper<NowPlayingMoviesData, NowPlayingMoviesDTONetwork>
    }
}