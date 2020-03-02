package com.intact.moviesbox.ui.movieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.intact.moviesbox.R
import com.intact.moviesbox.databinding.ActivityMovieDetailBinding
import com.intact.moviesbox.ui.base.BaseActivity
import com.intact.moviesbox.util.ActivityUtils
import com.intact.moviesbox.util.MOVIE_ID
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


/**
 * Activity to show the movie details
 *
 * Created by Anurag Garg on 2019-04-24.
 */
class MovieDetailActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var binding: ActivityMovieDetailBinding

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
        showMovieDetailFrag(intent!!.extras!!.getString(MOVIE_ID)!!)
    }

    // show movie detail frag
    private fun showMovieDetailFrag(movieId: String) {
        // initializing fragment
        val fragment = MovieDetailFragment.newInstance(movieId)

        // replace fragment
        ActivityUtils.replaceFragmentInActivity(
            supportFragmentManager,
            fragment,
            R.id.fragment_container
        )
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector
}