package com.intact.filmireview.di.module

import android.content.Context
import com.intact.filmireview.data.BaseDataManager
import com.intact.filmireview.data.DataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Anurag Garg on 19/03/19.
 *
 * Module to provide the generic dependencies
 */
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun providesDataManager(dataManager: DataManager) : BaseDataManager = dataManager
}