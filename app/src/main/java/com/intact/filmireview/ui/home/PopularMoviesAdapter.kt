package com.intact.filmireview.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.intact.filmireview.R
import com.intact.filmireview.ui.model.PopularMovieDTO
import com.intact.filmireview.util.IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PopularMoviesAdapter @Inject constructor(
    private val context: Context,
    private val picasso: Picasso
) :
    RecyclerView.Adapter<PopularMoviesAdapter.PopularViewHolder>() {

    private var popularMovies = ArrayList<PopularMovieDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PopularViewHolder(LayoutInflater.from(context).inflate(R.layout.item_popular_movie, parent, false))

    override fun getItemCount() = popularMovies.size

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        with(holder) {

            with(popularMovies[position]) {
                movieNameTV.text = title
                picasso.load(IMAGE_BASE_URL + posterPath).into(movieBannerImage)
            }
        }
    }

    class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.tvMovieName)
        lateinit var movieNameTV: TextView

        @BindView(R.id.imageView)
        lateinit var movieBannerImage: ImageView

        init {
            ButterKnife.bind(this@PopularViewHolder, view)
        }
    }

    fun setMoviesData(movies: ArrayList<PopularMovieDTO>) {
        popularMovies = movies
        notifyDataSetChanged()
    }
}