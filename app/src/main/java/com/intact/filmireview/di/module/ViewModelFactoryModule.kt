package com.intact.filmireview.di.module

import androidx.lifecycle.ViewModelProvider
import com.intact.filmireview.ui.CustomViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


/**
 *
 * creating custom view model factory
 *
 * Created by Anurag Garg on 25/03/19.
 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: CustomViewModelFactory): ViewModelProvider.Factory
}