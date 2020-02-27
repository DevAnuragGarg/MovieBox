package com.intact.moviesbox.di.module

import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import com.intact.moviesbox.util.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SchedulerModule {

    @Provides
    internal fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }
}
