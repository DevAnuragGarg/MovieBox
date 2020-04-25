package com.intact.moviesbox.ui.adapters

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intact.moviesbox.R
import com.intact.moviesbox.databinding.ItemMovieBinding
import com.intact.moviesbox.databinding.ItemPosterMovieBinding
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.ui.listeners.OnMovieItemClickListener
import com.intact.moviesbox.util.IMAGE_BASE_URL_500
import com.intact.moviesbox.util.MovieListType
import com.intact.moviesbox.util.createAndShowNotification
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import javax.inject.Inject

/**
 * Using list adapter to input the diff util callback
 * DiffUtil is a utility class that calculates the difference between two lists and outputs a list
 * of update operations that converts the first list into the second one. It can be used to
 * calculate updates for a RecyclerView Adapter. See ListAdapter and AsyncListDiffer which can
 * simplify the use of DiffUtil on a background thread.
 */
class MoviesAdapter @Inject constructor(
    private val context: Context,
    private val picasso: Picasso
) : ListAdapter<MovieDTO, RecyclerView.ViewHolder>(MoviesDiffCallback()) {

    private lateinit var movieType: MovieListType
    private lateinit var onClickListener: OnMovieItemClickListener

    fun setMovieListType(movieListType: MovieListType) {
        movieType = movieListType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        // as per the code lab the inflation should happen inside the view holder
        return when (movieType) {
            MovieListType.NowPlayingMovies -> MovieViewHolder.from(context, parent)
            MovieListType.TopRatedMovies -> PosterMovieViewHolder.from(context, parent)
            MovieListType.UpcomingMovies -> MovieViewHolder.from(context, parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (movieType) {
            MovieListType.NowPlayingMovies -> {
                with(holder as MovieViewHolder) {
                    with(getItem(position)) {
                        binding.tvMovieName.text = title
                        picasso.load(IMAGE_BASE_URL_500 + posterPath)
                            .placeholder(R.drawable.ic_video_camera).into(binding.bannerIV)

                        itemView.setOnClickListener {
                            onClickListener.onMovieItemClicked(
                                movie = getItem(position),
                                imageView = binding.bannerIV
                            )
                        }
                    }
                }
            }
            MovieListType.TopRatedMovies -> {
                with(holder as PosterMovieViewHolder) {
                    with(getItem(position)) {
                        binding.tvMovieName.text = title
                        binding.ratingBar.rating = voteAverage!!.div(2)
                        binding.tvRating.text = voteAverage.toString()
                        picasso.load(IMAGE_BASE_URL_500 + backdropPath)
                            .placeholder(R.drawable.ic_video_camera).into(binding.bannerIV)

                        itemView.setOnClickListener {
                            onClickListener.onMovieItemClicked(
                                movie = getItem(position),
                                imageView = binding.bannerIV
                            )
                        }
                    }
                }
            }
            MovieListType.UpcomingMovies -> {
                with(holder as MovieViewHolder) {
                    with(getItem(position)) {
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
                            12, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        binding.tvReleaseDate.text = spannableString
                        binding.tvReleaseDate.visibility = View.VISIBLE
                        picasso.load(IMAGE_BASE_URL_500 + posterPath)
                            .placeholder(R.drawable.ic_video_camera).into(binding.bannerIV)

                        itemView.setOnClickListener {
                            createAndShowNotification(context, title, overview)
                            onClickListener.onMovieItemClicked(
                                movie = getItem(position),
                                imageView = binding.bannerIV
                            )
                        }
                    }
                }
            }
        }
    }

    // view holder class for showing movie in tiles
    class MovieViewHolder private constructor(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(context: Context, parent: ViewGroup): MovieViewHolder {
                val view = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
                return MovieViewHolder(view)
            }
        }
    }

    // view holder class for showing posters
    class PosterMovieViewHolder private constructor(val binding: ItemPosterMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(context: Context, parent: ViewGroup): PosterMovieViewHolder {
                val view =
                    ItemPosterMovieBinding.inflate(LayoutInflater.from(context), parent, false)
                return PosterMovieViewHolder(view)
            }
        }
    }

    fun setMovieItemClickListener(listener: OnMovieItemClickListener) {
        onClickListener = listener
    }
}

// Implementing diff util
class MoviesDiffCallback : DiffUtil.ItemCallback<MovieDTO>() {

    // tests whether the two passed-in MovieDTO items, oldItem and newItem, are the same. If the
    // items have the same id, they are the same item, so return true. Otherwise, return false.
    // DiffUtil uses this test to help discover if an item was added, removed, or moved.
    override fun areItemsTheSame(oldItem: MovieDTO, newItem: MovieDTO) = oldItem.id == newItem.id

    // check whether oldItem and newItem contain the same data; that is, whether they are equal.
    // This equality check will check all the fields, because MovieDTO is a data class. Data classes
    // automatically define equals and a few other methods for you. If there are differences between
    // oldItem and newItem, this code tells DiffUtil that the item has been updated.
    override fun areContentsTheSame(oldItem: MovieDTO, newItem: MovieDTO) = oldItem == newItem

}