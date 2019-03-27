package com.intact.filmireview.di.module

import com.intact.filmireview.util.scheduler.BaseSchedulerProvider
import com.intact.filmireview.util.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SchedulerModule {

    @Provides
    internal fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }
}
