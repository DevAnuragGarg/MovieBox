package com.intact.moviesbox.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Anurag Garg on 25/03/19.
 */
abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun onBind(position: Int)
}