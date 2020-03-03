package com.intact.moviesbox.ui.base

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intact.moviesbox.R
import com.intact.moviesbox.databinding.ItemMovieBinding
import com.intact.moviesbox.databinding.ItemPosterMovieBinding
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.ui.movieDetail.MovieDetailActivity
import com.intact.moviesbox.util.IMAGE_BASE_URL_500
import com.intact.moviesbox.util.MOVIE_ID
import com.intact.moviesbox.util.MovieListType
import com.squareup.picasso.Picasso
import timber.log.Timber
import javax.inject.Inject

class BaseMoviesAdapter @Inject constructor(
    private val context: Context,
    private val picasso: Picasso,
    private val movieType: MovieListType
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        Timber.d("MovieType: ${movieType.name}")
    }

    private var moviesData = ArrayList<MovieDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (movieType) {
            MovieListType.NowPlayingMovies -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(view)
            }
            MovieListType.TopRatedMovies -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_poster_movie, parent, false)
                PosterMovieViewHolder(view)
            }
            MovieListType.UpcomingMovies -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(view)
            }
        }
    }

    override fun getItemCount() = moviesData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (movieType) {
            MovieListType.NowPlayingMovies -> {
                with(holder as MovieViewHolder) {
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
            MovieListType.TopRatedMovies -> {
                with(holder as PosterMovieViewHolder) {
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
            MovieListType.UpcomingMovies -> {
                with(holder as MovieViewHolder) {
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
        }
    }

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovieBinding.bind(view)!!
    }

    class PosterMovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPosterMovieBinding.bind(view)!!
    }

    fun setMoviesData(movies: ArrayList<MovieDTO>) {
        moviesData = movies
        notifyDataSetChanged()
    }
}