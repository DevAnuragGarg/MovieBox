package com.intact.moviesbox.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class CompositeDisposableModule {

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}
