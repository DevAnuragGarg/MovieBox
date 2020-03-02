package com.intact.moviesbox.ui.base

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intact.moviesbox.R
import com.intact.moviesbox.databinding.ItemMovieBinding
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.ui.movieDetail.MovieDetailActivity
import com.intact.moviesbox.util.IMAGE_BASE_URL_500
import com.intact.moviesbox.util.MOVIE_ID
import com.squareup.picasso.Picasso
import javax.inject.Inject

class BaseMoviesAdapter @Inject constructor(
    private val context: Context,
    private val picasso: Picasso
) :
    RecyclerView.Adapter<BaseMoviesAdapter.MovieViewHolder>() {

    private var moviesData = ArrayList<MovieDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = moviesData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder) {

            with(moviesData[position]) {
                binding.tvMovieName.text = title
                picasso.load(IMAGE_BASE_URL_500 + posterPath)
                    .placeholder(R.drawable.ic_video_camera).into(binding.bannerIV)

                itemView.setOnClickListener {
                    val intent = Intent(context, MovieDetailActivity::class.java)
                    intent.putExtra(MOVIE_ID, id)
                    context.startActivity(intent)
                }
            }
        }
    }

    class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setMoviesData(movies: ArrayList<MovieDTO>) {
        moviesData = movies
        notifyDataSetChanged()
    }
}