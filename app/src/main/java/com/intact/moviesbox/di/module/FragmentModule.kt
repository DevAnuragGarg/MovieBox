package com.intact.moviesbox.di.module

import com.intact.moviesbox.di.qualifiers.NowPlayingQualifier
import com.intact.moviesbox.di.qualifiers.TopRatedQualifier
import com.intact.moviesbox.di.qualifiers.UpcomingQualifier
import com.intact.moviesbox.ui.movieDetail.MovieListFragment
import com.intact.moviesbox.util.MovieListType
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    @NowPlayingQualifier
    fun provideNowPlayingMovieListFragment() =
        MovieListFragment.newInstance(movieListType = MovieListType.NowPlayingMovies.name)

    @Provides
    @TopRatedQualifier
    fun provideTopRatedMovieListFragment() =
        MovieListFragment.newInstance(movieListType = MovieListType.TopRatedMovies.name)

    @Provides
    @UpcomingQualifier
    fun provideUpcomingMovieListFragment() =
        MovieListFragment.newInstance(movieListType = MovieListType.UpcomingMovies.name)
}
