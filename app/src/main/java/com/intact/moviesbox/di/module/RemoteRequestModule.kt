package com.intact.moviesbox.di.module

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.NowPlayingMoviesData
import com.intact.moviesbox.data.model.TopRatedMoviesData
import com.intact.moviesbox.data.repository.RemoteDataSource
import com.intact.moviesbox.remote.api.MovieServiceRequests
import com.intact.moviesbox.remote.mapper.Mapper
import com.intact.moviesbox.remote.mapper.MovieDataNetworkMapper
import com.intact.moviesbox.remote.mapper.NowPlayingDataNetworkMapper
import com.intact.moviesbox.remote.mapper.TopRatedDataNetworkMapper
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.NowPlayingMoviesDTONetwork
import com.intact.moviesbox.remote.model.TopRatedMoviesDTONetwork
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
        fun bindNowPlayingDataNetworkMapper(nowPlayingDataNetworkMapper: NowPlayingDataNetworkMapper): Mapper<NowPlayingMoviesData, NowPlayingMoviesDTONetwork>

        @Binds
        fun bindTopRatedDataNetworkMapper(topRatedDataNetworkMapper: TopRatedDataNetworkMapper): Mapper<TopRatedMoviesData, TopRatedMoviesDTONetwork>
    }
}