package com.intact.moviesbox.ui.movieDetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.intact.moviesbox.databinding.ActivityMovieDetailBinding
import com.intact.moviesbox.ui.base.BaseActivity
import com.intact.moviesbox.ui.base.CustomViewModelFactory
import com.intact.moviesbox.util.MOVIE_ID
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


/**
 * Activity to show the movie details
 *
 * Created by Anurag Garg on 2019-04-24.
 */
class MovieDetailActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private var movieId: Long = 0
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeVariables()
    }

    private fun initializeVariables() {
        // set up action bar
        setSupportActionBar(binding.toolbar)
        actionBar?.run {
            this.setDisplayHomeAsUpEnabled(true)
        }

        // get the view model
        movieDetailViewModel =
            ViewModelProviders.of(this@MovieDetailActivity, viewModelFactory)
                .get(MovieDetailViewModel::class.java)
        //setObservers(movieDetailViewModel)
        getMovieId()

        // get the movie details
        movieDetailViewModel.getMovieDetails(movieId)

        // get movie details
        //getMovieDetails()
    }

    fun getMovieId() {
        movieId = intent.extras!!.getLong(MOVIE_ID);
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}