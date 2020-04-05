package com.intact.moviesbox.ui.movieDetail

import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Anurag Garg on 2020-04-03.
 */
@Module
abstract class MovieListFragmentModule {

    @ContributesAndroidInjector
    internal abstract fun provideMovieListFragment(): MovieListFragment
}