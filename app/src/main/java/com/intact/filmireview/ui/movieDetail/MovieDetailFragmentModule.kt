package com.intact.filmireview.ui.movieDetail

import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Anurag Garg on 2019-04-25.
 */
@Module
abstract class MovieDetailFragmentModule {

    @ContributesAndroidInjector
    internal abstract fun provideMovieDetailFragment(): MovieDetailFragment
}