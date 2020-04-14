package com.intact.moviesbox.ui.adapters

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.intact.moviesbox.R
import com.intact.moviesbox.databinding.ItemMovieBinding
import com.intact.moviesbox.databinding.ItemPosterMovieBinding
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.ui.listeners.OnMovieItemClickListener
import com.intact.moviesbox.util.IMAGE_BASE_URL_500
import com.intact.moviesbox.util.MovieListType
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import javax.inject.Inject

class MoviesAdapter @Inject constructor(
    private val context: Context,
    private val picasso: Picasso
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var moviesData = ArrayList<MovieDTO>()
    private lateinit var movieType: MovieListType
    private lateinit var onClickListener: OnMovieItemClickListener

    fun setMovieListType(movieListType: MovieListType) {
        movieType = movieListType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (movieType) {
            MovieListType.NowPlayingMovies -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(
                    view
                )
            }
            MovieListType.TopRatedMovies -> {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.item_poster_movie, parent, false)
                PosterMovieViewHolder(
                    view
                )
            }
            MovieListType.UpcomingMovies -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(
                    view
                )
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
                            onClickListener.onMovieItemClicked(movie = moviesData[position])
                        }
                    }
                }
            }
            MovieListType.TopRatedMovies -> {
                with(holder as PosterMovieViewHolder) {
                    with(moviesData[position]) {
                        binding.tvMovieName.text = title
                        binding.ratingBar.rating = voteAverage!!.div(2)
                        binding.tvRating.text = voteAverage.toString()
                        picasso.load(IMAGE_BASE_URL_500 + backdropPath)
                            .placeholder(R.drawable.ic_video_camera).into(binding.bannerIV)

                        itemView.setOnClickListener {
                            onClickListener.onMovieItemClicked(movie = moviesData[position])
                        }
                    }
                }
            }
            MovieListType.UpcomingMovies -> {
                with(holder as MovieViewHolder) {
                    with(moviesData[position]) {
                        binding.tvMovieName.text = title
                        binding.tvMovieName.maxLines = 1

                        // converting and setting date
                        val dateInstance = SimpleDateFormat("yyyy-MM-dd").parse(releaseDate)
                        val releaseDateFormat = SimpleDateFormat("dd MMM yy").format(dateInstance)

                        val spannableString = SpannableString(
                            String.format(
                                context.getString(R.string.release_on),
                                releaseDateFormat
                            )
                        )
                        spannableString.setSpan(
                            ForegroundColorSpan(
                                ContextCompat.getColor(
                                    context,
                                    R.color.rating_yellow
                                )
                            ),
                            12, spannableString.length - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        binding.tvReleaseDate.text = spannableString
                        binding.tvReleaseDate.visibility = View.VISIBLE
                        picasso.load(IMAGE_BASE_URL_500 + posterPath)
                            .placeholder(R.drawable.ic_video_camera).into(binding.bannerIV)

                        itemView.setOnClickListener {
                            onClickListener.onMovieItemClicked(movie = moviesData[position])
                        }
                    }
                }
            }
        }
    }

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemMovieBinding = ItemMovieBinding.bind(view)
    }

    class PosterMovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemPosterMovieBinding = ItemPosterMovieBinding.bind(view)
    }

    fun addMovieData(movies: ArrayList<MovieDTO>) {
        moviesData.addAll(movies)
        notifyDataSetChanged()
    }

    fun setMovieItemClickListener(listener: OnMovieItemClickListener) {
        onClickListener = listener
    }
}