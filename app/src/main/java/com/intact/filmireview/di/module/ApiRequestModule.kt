package com.intact.filmireview.di.module

import com.intact.filmireview.data.request.HomeRequests
import com.intact.filmireview.data.request.LoginRequests
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ApiRequestModule {

    @Provides
    @Singleton
    internal fun providesHomeApiRequests(retrofit: Retrofit) = retrofit.create(HomeRequests::class.java)

    @Provides
    @Singleton
    internal fun providesLoginApiRequests(retrofit: Retrofit) = retrofit.create(LoginRequests::class.java)
}