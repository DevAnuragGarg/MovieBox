package com.intact.moviesbox.ui.base

import android.content.res.Configuration
import android.widget.Toast
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
 * This all is done in DaggerActivity
 *
 *  Here we are checking what kind of night mode is enabled or disabled according to which we
 *  are showing the toast to the user
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    // show dark theme enabled or not using toast
    fun showDarkThemeEnabled() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                Toast.makeText(this, "NIGHT MODE DISABLED", Toast.LENGTH_SHORT).show()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                Toast.makeText(this, "NIGHT MODE ENABLED", Toast.LENGTH_SHORT).show()
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                Toast.makeText(this, "NIGHT MODE DISABLED", Toast.LENGTH_SHORT).show()
            }
        }
    }
}