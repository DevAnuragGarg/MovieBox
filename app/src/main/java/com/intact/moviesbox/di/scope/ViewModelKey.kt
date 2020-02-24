package com.intact.moviesbox.di.scope

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * to bind ViewModel and create these Map we need the multibindings and to do it we will use a
 * specific annotation, we will call it ViewModelKey, it’s represents the key of our map.
 */

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)