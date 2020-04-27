package com.intact.moviesbox.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.intact.moviesbox.R
import com.intact.moviesbox.databinding.ActivityMoviesListBinding
import com.intact.moviesbox.presentation.viewmodels.FragmentListViewModel
import com.intact.moviesbox.ui.base.BaseActivity
import com.intact.moviesbox.ui.base.CustomViewModelFactory
import com.intact.moviesbox.util.INTENT_KEY_SHOW_POPULAR_MOVIES
import com.intact.moviesbox.util.INTENT_KEY_SHOW_TRENDING_MOVIES
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
        val showPopularMovies =
            intent?.extras?.getBoolean(INTENT_KEY_SHOW_POPULAR_MOVIES, false) ?: false
        val showTrendingMovies =
            intent?.extras?.getBoolean(INTENT_KEY_SHOW_TRENDING_MOVIES, false) ?: false

        // set the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.run {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
        }

        // set the toolbar title
        if (showPopularMovies)
            supportActionBar!!.title = getString(R.string.popular_movies_title)
        else if (showTrendingMovies)
            supportActionBar!!.title = getString(R.string.trending_movies_title)

        // get the view model
        val homeViewModel = ViewModelProviders.of(this@MoviesListActivity, viewModelFactory)
            .get(FragmentListViewModel::class.java)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}