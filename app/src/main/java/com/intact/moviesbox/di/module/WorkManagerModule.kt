package com.intact.moviesbox.di.module

import androidx.work.Configuration
import com.intact.moviesbox.util.worker.CustomWorkFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WorkManagerModule {
    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
        customWorkFactory: CustomWorkFactory
    ): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(customWorkFactory)
            .build()
    }
}