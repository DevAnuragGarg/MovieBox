package com.intact.moviesbox.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection


/**
 * Created by Anurag Garg on 19/03/19.
 *
 * Base activity that will be extended by all the activities
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // Activity should not know about how it is injected. So generic.
        // All the dependencies will be injected automatically
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
    }
}