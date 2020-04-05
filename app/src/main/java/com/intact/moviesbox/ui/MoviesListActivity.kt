package com.intact.moviesbox.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.intact.moviesbox.databinding.ActivityMoviesListBinding
import com.intact.moviesbox.presentation.viewmodels.HomeViewModel
import com.intact.moviesbox.ui.base.BaseActivity
import com.intact.moviesbox.ui.base.CustomViewModelFactory
import com.intact.moviesbox.util.SHOW_POPULAR_MOVIES
import javax.inject.Inject

class MoviesListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var binding: ActivityMoviesListBinding      // view binding jet-pack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeVariables()
    }

    private fun initializeVariables() {
        val showPopularMovies = intent?.extras?.getBoolean(SHOW_POPULAR_MOVIES, false) ?: false
        val showTrendingMovies = intent?.extras?.getBoolean(SHOW_POPULAR_MOVIES, false) ?: false

        // get the view model
        val homeViewModel = ViewModelProviders.of(this@MoviesListActivity, viewModelFactory)
            .get(HomeViewModel::class.java)
        //setObservers(homeViewModel)
        when {
            showPopularMovies -> {
                homeViewModel.getNowPlayingMovies("1")
            }
            showTrendingMovies -> {
                homeViewModel.getTopRatedMovies("1")
            }
        }
    }

    /*// setting the observers
    private fun setObservers(viewModel: HomeViewModel) {
        observeLiveData(viewModel.getNowPlayingMoviesLiveData()) {
            Timber.d("Updating playing now movies data")
            // nowPlayingMoviesAdapter.setMoviesData(it)
        }
        observeLiveData(viewModel.getTopRatedMoviesLiveData()) {
            Timber.d("Updating top rated movies data")
            // topRatedMoviesAdapter.setMoviesData(it)
        }
    }*/
}