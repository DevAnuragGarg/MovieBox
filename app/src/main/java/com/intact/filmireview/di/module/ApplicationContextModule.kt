package com.intact.filmireview.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ApplicationContextModule {

    @Binds
    @Singleton
    abstract fun providesApplicationContext(application: Application): Context
}