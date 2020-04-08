package com.intact.moviesbox.di.module

import com.intact.moviesbox.ui.fragment.MovieListFragment
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