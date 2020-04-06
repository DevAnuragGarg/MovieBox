package com.intact.moviesbox.ui.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.intact.moviesbox.R
import dagger.android.support.AndroidSupportInjection


/**
 * Created by Anurag Garg on 2019-04-24.
 */
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    fun showToast(toastText: String, duration: Int, gravity: Int = 0) {
        val toast = Toast.makeText(activity, toastText, duration)
        toast.setGravity(gravity, 0, 0)
        toast.show()
    }

    // created customized toast
    fun showCustomizedToast(
        toastText: String,
        toastDuration: Int,
        gravity: Int = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
    ) {
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast_layout, null)
        val text: TextView = layout.findViewById(R.id.toastMessage)
        text.text = toastText
        with(Toast(activity)) {
            setGravity(gravity, 0, 0)
            duration = toastDuration
            view = layout
            show()
        }
    }

    // created customized toast
    fun showCustomizedSnackBar(
        view: View,
        textMessage: String,
        snackBarDuration: Int,
        showCustomizedSnackBar: Boolean = false
    ) {
        val snackBar = Snackbar.make(view, textMessage, snackBarDuration)

        if (showCustomizedSnackBar) {
            // styling for action text
            snackBar.setActionTextColor(Color.WHITE)

            // styling for rest of text
            val snackBarView = snackBar.view
            val textView =
                snackBarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.WHITE)
            textView.isAllCaps = false
            textView.textSize = 16f
            textView.maxLines = 2
            textView.ellipsize = TextUtils.TruncateAt.END

            // styling for background of snack bar
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    activity as Context,
                    R.color.colorAccent
                )
            )

            snackBar.setAction("DISMISS") {
                snackBar.dismiss()
            }
            snackBar.duration = Snackbar.LENGTH_INDEFINITE
        }

        snackBar.show()
    }
}