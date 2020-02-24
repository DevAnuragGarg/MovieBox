package com.intact.moviesbox.ui.movieDetail

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import com.intact.moviesbox.R
import com.intact.moviesbox.ui.BaseActivity
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

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initializeVariables()
    }

    private fun initializeVariables() {
        //initialize butterknife
        ButterKnife.bind(this)

        // set up action bar
        setSupportActionBar(toolbar)
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
        ActivityUtils.replaceFragmentInActivity(supportFragmentManager, fragment, R.id.fragment_container)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector
}