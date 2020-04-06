package com.intact.moviesbox.ui.base

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
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
}