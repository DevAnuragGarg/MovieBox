package com.intact.filmireview.di.module

import com.intact.filmireview.BuildConfig
import com.intact.filmireview.data.interceptor.APIInterceptor
import com.intact.filmireview.util.BASE_URL
import com.intact.filmireview.util.CONNECTION_TIME_OUT
import com.intact.filmireview.util.READ_TIME_OUT
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun providesOkHttp(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        // creating okhttp client
        return OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(APIInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Well in order for our
            // calls to return type Observable, we have to set the call adapter to RxJavaCallAdapter.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}