package com.intact.filmireview.util

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


/**
 * This provides methods to help Activities load their UI.
 *
 * Created by Anurag Garg on 2019-04-25.
 */
object ActivityUtils {

    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     *
     */
    fun replaceFragmentInActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment, frameId: Int
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        transaction.commit()
    }

    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     *
     */
    fun replaceFragmentInActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment, tag: String
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(fragment, tag)
        transaction.commit()
    }
}