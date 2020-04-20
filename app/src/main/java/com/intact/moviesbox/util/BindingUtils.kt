package com.intact.moviesbox.util

import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * Binding adapters are responsible for making the appropriate framework calls to set values.
 * One example is setting a property value like calling the setText() method. Another example is
 * setting an event listener like calling the setOnClickListener() method.
 * The Data Binding Library allows you to specify the method called to set a value, provide your
 * own binding logic, and specify the type of the returned object by using adapters.
 */
// extension function
@BindingAdapter("formattedReleaseDate")
fun MaterialTextView.setFormattedReleaseDate(releaseDate: String?) {
    Timber.d("Release date: $releaseDate")

    releaseDate?.let {
        // converting and setting date
        val dateInstance =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(releaseDate)
        text = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(dateInstance)
    }
}
