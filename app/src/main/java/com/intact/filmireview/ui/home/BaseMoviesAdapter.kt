package com.intact.filmireview.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.intact.filmireview.R
import com.intact.filmireview.ui.model.MovieDTO
import com.intact.filmireview.ui.movieDetail.MovieDetailActivity
import com.intact.filmireview.util.IMAGE_BASE_URL_500
import com.intact.filmireview.util.MOVIE_ID
import com.squareup.picasso.Picasso
import javax.inject.Inject

class BaseMoviesAdapter @Inject constructor(
    private val context: Context,
    private val picasso: Picasso
) :
    RecyclerView.Adapter<BaseMoviesAdapter.MovieViewHolder>() {

    private var moviesData = ArrayList<MovieDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))

    override fun getItemCount() = moviesData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder) {

            with(moviesData[position]) {
                movieNameTV.text = title
                picasso.load(IMAGE_BASE_URL_500 + posterPath).into(movieBannerImage)

                itemView.setOnClickListener {
                    val intent = Intent(context, MovieDetailActivity::class.java)
                    intent.putExtra(MOVIE_ID, id)
                    context.startActivity(intent)
                }
            }
        }
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.tvMovieName)
        lateinit var movieNameTV: TextView

        @BindView(R.id.imageView)
        lateinit var movieBannerImage: ImageView

        init {
            ButterKnife.bind(this@MovieViewHolder, view)
        }
    }

    fun setMoviesData(movies: ArrayList<MovieDTO>) {
        moviesData = movies
        notifyDataSetChanged()
    }
}