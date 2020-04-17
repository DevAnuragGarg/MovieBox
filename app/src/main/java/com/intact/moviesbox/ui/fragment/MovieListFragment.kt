package com.intact.moviesbox.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.intact.moviesbox.R
import com.intact.moviesbox.databinding.FragmentMovieListBinding
import com.intact.moviesbox.extension.observeLiveData
import com.intact.moviesbox.presentation.model.ErrorDTO
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.viewmodels.FragmentListViewModel
import com.intact.moviesbox.ui.base.BaseFragment
import com.intact.moviesbox.ui.base.CustomViewModelFactory
import com.intact.moviesbox.ui.adapters.MoviesAdapter
import com.intact.moviesbox.ui.listeners.OnMovieItemClickListener
import com.intact.moviesbox.ui.activity.MovieDetailActivity
import com.intact.moviesbox.util.INTENT_KEY_MOVIE_ID
import com.intact.moviesbox.util.INTENT_KEY_MOVIE_LIST_TYPE
import com.intact.moviesbox.util.MovieListType
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Anurag Garg on 2020-04-03.
 */
class MovieListFragment : BaseFragment(), OnMovieItemClickListener {

    @Inject
    lateinit var movieListAdapter: MoviesAdapter

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private var pageNumber = "1"
    private val binding get() = _binding!!
    private lateinit var movieListType: MovieListType
    private var _binding: FragmentMovieListBinding? = null
    private lateinit var fragmentListViewModel: FragmentListViewModel

    // creating new instance using static function
    companion object {

        fun newInstance(movieListType: String) = MovieListFragment()
            .apply {
            arguments = bundleOf(
                INTENT_KEY_MOVIE_LIST_TYPE to movieListType
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieListType = MovieListType.valueOf(arguments?.getString(INTENT_KEY_MOVIE_LIST_TYPE)!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // get the view model
        fragmentListViewModel =
            ViewModelProviders.of(this@MovieListFragment, viewModelFactory)
                .get(FragmentListViewModel::class.java)

        // observe the loading live data
        observeLiveData(fragmentListViewModel.getLoadingProgressLiveData()) {
            updateProgressBar(showProgressBar = it)
        }

        // get the movie details
        when (movieListType) {
            MovieListType.TopRatedMovies -> {
                fragmentListViewModel.getTopRatedMovies(pageNumber = pageNumber)
                observeLiveData(fragmentListViewModel.getTopRatedMoviesListLiveData()) {
                    updateUI(it)
                }
                observeLiveData(fragmentListViewModel.getTopRatedErrorLiveData()) {
                    onError(it)
                }
            }
            MovieListType.NowPlayingMovies -> {
                fragmentListViewModel.getNowPlayingMovies(pageNumber = pageNumber)
                observeLiveData(fragmentListViewModel.getNowPlayingMoviesListLiveData()) {
                    updateUI(it)
                }
                observeLiveData(fragmentListViewModel.getNowPlayingErrorLiveData()) {
                    onError(it)
                }
            }
            MovieListType.UpcomingMovies -> {
                fragmentListViewModel.getUpcomingMovies(pageNumber = pageNumber)
                observeLiveData(fragmentListViewModel.getUpcomingMoviesListLiveData()) {
                    updateUI(it)
                }
                observeLiveData(fragmentListViewModel.getUpcomingErrorLiveData()) {
                    onError(it)
                }
            }
        }

        // set up the click listener
        movieListAdapter.setMovieItemClickListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        initializeVariables()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeVariables() {
        Timber.d("Initialize variables")

        // setting up the title
        // get the movie details
        when (movieListType) {
            MovieListType.TopRatedMovies -> {
                binding.titleTV.text = getString(R.string.top_rated_movies_title)
            }
            MovieListType.NowPlayingMovies -> {
                binding.titleTV.text = getString(R.string.now_playing_movies_title)
            }
            MovieListType.UpcomingMovies -> {
                binding.titleTV.text = getString(R.string.upcoming_movies_title)
            }
        }

        // setup recycler view
        movieListAdapter.setMovieListType(movieListType)
        with(binding.moviesRecyclerView) {
            layoutManager =
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            adapter = movieListAdapter
        }
    }

    // updating the UI
    private fun updateUI(movieListData: ArrayList<MovieDTO>) {
        movieListAdapter.addMovieData(movieListData)
    }

    // on error received
    private fun onError(dto: ErrorDTO) {
        Timber.d("${movieListType.name}: onError: $dto")
        when (movieListType) {
            MovieListType.UpcomingMovies ->
                showCustomizedSnackBar(
                    binding.parentLayout,
                    "Upcoming movies: ${dto.message}",
                    Snackbar.LENGTH_LONG,
                    true
                )

            MovieListType.NowPlayingMovies ->
                showCustomizedToast(
                    "Now Playing movies: ${dto.message}",
                    Toast.LENGTH_LONG,
                    Gravity.TOP or Gravity.CENTER_HORIZONTAL
                )

            MovieListType.TopRatedMovies ->
                showCustomizedToast(
                    "Top Rated movies: ${dto.message}",
                    Toast.LENGTH_LONG,
                    Gravity.CENTER
                )
        }
    }

    private fun updateProgressBar(showProgressBar: Boolean) {
        if (showProgressBar) {
            binding.progressBar.visibility = VISIBLE
            binding.moviesRecyclerView.visibility = GONE
        } else {
            binding.progressBar.visibility = GONE
            binding.moviesRecyclerView.visibility = VISIBLE
        }
    }

    override fun onMovieItemClicked(movie: MovieDTO) {
        // saving the movie details
        fragmentListViewModel.saveMovieDetail(movieDTO = movie)

        // starting movie detail activity
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(INTENT_KEY_MOVIE_ID, movie.id)
        startActivity(intent)
    }
}