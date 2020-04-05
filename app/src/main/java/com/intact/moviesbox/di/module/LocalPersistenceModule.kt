package com.intact.moviesbox.di.module

import android.app.Application
import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.repository.LocalDataSource
import com.intact.moviesbox.local.database.MoviesBoxDB
import com.intact.moviesbox.local.entity.MovieEntity
import com.intact.moviesbox.local.mapper.Mapper
import com.intact.moviesbox.local.mapper.MovieLocalDataMapper
import com.intact.moviesbox.local.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalPersistenceModule.Binders::class])
class LocalPersistenceModule {

    @Module
    interface Binders {

        @Binds
        fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

        @Binds
        fun bindMovieMapper(
            movieLocalDataMapper: MovieLocalDataMapper
        ): Mapper<MovieEntity, MovieDataDTO>
    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = MoviesBoxDB.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesMovieDAO(
        moviesBoxDB: MoviesBoxDB
    ) = moviesBoxDB.getMovieDAO()
}