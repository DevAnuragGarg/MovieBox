package com.intact.moviesbox.di.module

import android.content.Context
import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import com.intact.moviesbox.ui.adapters.MoviesAdapter
import com.intact.moviesbox.util.SchedulerProvider
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Anurag Garg on 19/03/19.
 *
 * Module to provide the generic dependencies
 */
@Module
class AppModule {

    @Provides
    internal fun provideSchedulerProvider(): BaseSchedulerProvider =
        SchedulerProvider()

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideNowPlayingMovieAdapter(context: Context, picasso: Picasso) =
        MoviesAdapter(context, picasso)
}