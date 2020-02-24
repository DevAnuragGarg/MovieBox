package com.intact.moviesbox.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


/**
 * Created by Anurag Garg on 26/03/19.
 */

/**
 * The crossinline marker is used to mark lambdas that mustn’t
 * allow non-local returns, especially when such lambda is passed
 * to another execution context such as a higher order function
 * that is not inlined, a local object or a nested function.
 * In other words, you won’t be able to do a return in such lambdas.
 */

inline fun <T> LifecycleOwner.observeLiveData(data: LiveData<T>, crossinline onChanged: (T) -> Unit) {
    data.observe(this, Observer {
        it?.let { value -> onChanged(value) }
    })
}