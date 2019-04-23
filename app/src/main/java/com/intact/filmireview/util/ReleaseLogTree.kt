package com.intact.filmireview.util

import android.util.Log
import timber.log.Timber
import java.util.logging.Level


/**
 * Created by Anurag Garg on 2019-04-22.
 */
class ReleaseLogTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Level.SEVERE.intValue() || priority == Level.WARNING.intValue()) {

        }
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {

        // Don't log VERBOSE, DEBUG and INFO
        return !(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO)
    }
}