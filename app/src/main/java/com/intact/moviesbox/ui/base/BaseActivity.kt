package com.intact.moviesbox.ui.base

import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Anurag Garg on 19/03/19.
 *
 * Base activity that will be extended by all the activities
 *
 * DaggerAppCompatActivity: you don not need to use AndroidInjection.inject(this) in onCreate
 * Moreover you no need to extend the HasAndroidInjector,
 * @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
 * override fun androidInjector(): AndroidInjector<Any> = androidInjector
 *
 * This all is done in DaggerActivity
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

}