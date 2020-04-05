package com.intact.moviesbox.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.intact.moviesbox.R
import com.intact.moviesbox.databinding.FragmentMovieListBinding
import com.intact.moviesbox.di.qualifiers.NowPlayingQualifier
import com.intact.moviesbox.extension.observeLiveData
import com.intact.moviesbox.presentation.model.ErrorDTO
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.viewmodels.HomeViewModel
import com.intact.moviesbox.ui.base.BaseFragment
import com.intact.moviesbox.ui.base.CustomViewModelFactory
import com.intact.moviesbox.ui.base.MoviesAdapter
import com.intact.moviesbox.util.MOVIE_LIST_TYPE
import com.intact.moviesbox.util.MovieListType
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Anurag Garg on 2020-04-03.
 */
class MovieListFragment : BaseFragment() {

    @Inject
    @NowPlayingQualifier
    lateinit var movieListAdapter: MoviesAdapter

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private var pageNumber = "1"
    private lateinit var movieListType: MovieListType
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentMovieListBinding

    // creating new instance using static function
    companion object {

        fun newInstance(movieListType: String) = MovieListFragment().apply {
            arguments = bundleOf(
                MOVIE_LIST_TYPE to movieListType
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieListType = MovieListType.valueOf(arguments?.getString(MOVIE_LIST_TYPE)!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // get the view model
        homeViewModel =
            ViewModelProviders.of(this@MovieListFragment, viewModelFactory)
                .get(HomeViewModel::class.java)

        Timber.d("Check: $homeViewModel")

        // get the movie details
        when (movieListType) {
            MovieListType.TopRatedMovies -> {
                homeViewModel.getTopRatedMovies(pageNumber = pageNumber)
                observeLiveData(homeViewModel.getTopRatedMoviesListLiveData()) {
                    updateUI(it)
                }
                observeLiveData(homeViewModel.getTopRatedErrorLiveData()) {
                    onError(it)
                }
            }
            MovieListType.NowPlayingMovies -> {
                homeViewModel.getNowPlayingMovies(pageNumber = pageNumber)
                observeLiveData(homeViewModel.getNowPlayingMoviesListLiveData()) {
                    updateUI(it)
                }
                observeLiveData(homeViewModel.getNowPlayingErrorLiveData()) {
                    onError(it)
                }
            }
            MovieListType.UpcomingMovies -> {
                homeViewModel.getUpcomingMovies(pageNumber = pageNumber)
                observeLiveData(homeViewModel.getUpcomingMoviesListLiveData()) {
                    updateUI(it)
                }
                observeLiveData(homeViewModel.getUpcomingErrorLiveData()) {
                    onError(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        initializeVariables()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

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
        Timber.d("onError: $dto")
    }
}