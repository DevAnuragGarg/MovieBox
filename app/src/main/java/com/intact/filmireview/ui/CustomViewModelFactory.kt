package com.intact.filmireview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


/**
 *
 * We can not create ViewModel on our own.
 * We need ViewModelProviders utility provided by Android to create ViewModels.
 * But ViewModelProviders can only instantiate ViewModels with no arg constructor.
 * So if I have a ViewModel with multiple arguments, then I need to use a Factory
 * that I can pass to ViewModelProviders to use when an instance of MyViewModel is required.
 *
 * Created by Anurag Garg on 25/03/19.
 */

@Suppress("UNCHECKED_CAST")
class CustomViewModelFactory @Inject constructor(private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass] ?: viewModelsMap.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}