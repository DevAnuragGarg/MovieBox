package com.intact.filmireview.di.module

import com.intact.filmireview.di.scope.ActivityScope
import com.intact.filmireview.ui.home.HomeActivity
import com.intact.filmireview.ui.movieDetail.MovieDetailActivity
import com.intact.filmireview.ui.movieDetail.MovieDetailFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * The ActivityBuilder above binds the modules with the MainActivity. Previously,
 * we have to use custom scopes to confine objects to particular modules, or
 * Android Views (Activities, Fragments) — such as confining a Presenter’s instance
 * to it’s associated Activity. However, the Dagger Android libraries now simplify
 * this to such a degree that we no longer need to provide a Component for the
 * MainActivityModule or any other activity level Dagger Module
 */

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MovieDetailFragmentModule::class])
    abstract fun contributeMovieDetailActivity(): MovieDetailActivity
}