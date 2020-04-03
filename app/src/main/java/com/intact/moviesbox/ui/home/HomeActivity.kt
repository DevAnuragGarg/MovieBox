package com.intact.moviesbox.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.intact.moviesbox.R
import com.intact.moviesbox.databinding.ActivityHomeBinding
import com.intact.moviesbox.di.qualifiers.NowPlayingQualifier
import com.intact.moviesbox.di.qualifiers.TopRatedQualifier
import com.intact.moviesbox.di.qualifiers.UpcomingQualifier
import com.intact.moviesbox.extension.observeLiveData
import com.intact.moviesbox.presentation.model.ErrorDTO
import com.intact.moviesbox.presentation.model.MovieDTO
import com.intact.moviesbox.presentation.viewmodels.HomeViewModel
import com.intact.moviesbox.ui.MoviesListActivity
import com.intact.moviesbox.ui.base.BaseActivity
import com.intact.moviesbox.ui.base.CustomViewModelFactory
import com.intact.moviesbox.ui.base.MoviesAdapter
import com.intact.moviesbox.ui.listeners.OnMovieItemClickListener
import com.intact.moviesbox.ui.movieDetail.MovieDetailActivity
import com.intact.moviesbox.util.MOVIE_ID
import com.intact.moviesbox.util.REQUEST_CODE_UPDATE
import com.intact.moviesbox.util.SHOW_POPULAR_MOVIES
import com.intact.moviesbox.util.SHOW_TRENDING_MOVIES
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity(), OnMovieItemClickListener {

    @Inject
    @NowPlayingQualifier
    lateinit var nowPlayingMoviesAdapter: MoviesAdapter

    @Inject
    @TopRatedQualifier
    lateinit var topRatedMoviesAdapter: MoviesAdapter

    @Inject
    @UpcomingQualifier
    lateinit var upcomingMoviesAdapter: MoviesAdapter

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var binding: ActivityHomeBinding              // view binding jet-pack
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var appUpdateManager: AppUpdateManager
    private val installUpdateListener: InstallStateUpdatedListener =
        InstallStateUpdatedListener { state ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                Timber.d("Download update is complete")
                popupSnackBarForCompleteUpdate()
            } else {
                Timber.d("Download status: ${state.installStatus()} and Error code: ${state.installErrorCode()}")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeVariables()
    }

    private fun initializeVariables() {

        // check if there is a new update
        checkUpdate()

        // set the action bar
        setSupportActionBar(binding.toolbar)

        // initializing app center for continous integration
        AppCenter.start(
            application, "5a8030df-7ef1-4007-a0f7-807d8d6dd058",
            Analytics::class.java, Crashes::class.java
        )

        // update empty UI
        updateNowPlayingMoviesUI()
        updatedTopRatedMoviesUI()
        updateUpcomingMoviesUI()

        // get the view model
        homeViewModel = ViewModelProviders.of(this@HomeActivity, viewModelFactory)
            .get(HomeViewModel::class.java)
        setObservers(homeViewModel)
        homeViewModel.getNowPlayingMovies("1")
        homeViewModel.getTopRatedMovies("1")
        homeViewModel.getUpcomingMovies("1")
    }

    // Checks that the update is not stalled during 'onResume()'.
    // However, you should execute this check at all app entry points.
    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackBarForCompleteUpdate()
            }
        }
    }

    // updating the now playing movies UI
    private fun updateNowPlayingMoviesUI() {
        nowPlayingMoviesAdapter.setMoviesData(ArrayList())
        nowPlayingMoviesAdapter.setMovieItemClickListener(this)

        with(binding.nowPlayingRecyclerView) {
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingMoviesAdapter
            nowPlayingMoviesAdapter.notifyDataSetChanged()
        }
    }

    // updating the upcoming movies UI
    private fun updateUpcomingMoviesUI() {
        upcomingMoviesAdapter.setMoviesData(ArrayList())
        upcomingMoviesAdapter.setMovieItemClickListener(this)

        with(binding.upcomingMoviesRecyclerView) {
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingMoviesAdapter
            upcomingMoviesAdapter.notifyDataSetChanged()
        }
    }

    // updating the top rated movies UI
    private fun updatedTopRatedMoviesUI() {
        topRatedMoviesAdapter.setMoviesData(ArrayList())
        topRatedMoviesAdapter.setMovieItemClickListener(this)

        with(binding.topRatedMoviesRecyclerView) {
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedMoviesAdapter
            topRatedMoviesAdapter.notifyDataSetChanged()
        }
    }

    // setting the observers
    private fun setObservers(viewModel: HomeViewModel) {
        observeLiveData(viewModel.getNowPlayingMoviesLiveData()) {
            Timber.d("Updating playing now movies data")
            nowPlayingMoviesAdapter.setMoviesData(it)
        }
        observeLiveData(viewModel.getTopRatedMoviesLiveData()) {
            Timber.d("Updating top rated movies data")
            topRatedMoviesAdapter.setMoviesData(it)
        }
        observeLiveData(viewModel.getUpcomingMoviesLiveData()) {
            Timber.d("Updating upcoming movies data")
            upcomingMoviesAdapter.setMoviesData(it)
        }
        observeLiveData(viewModel.getErrorLiveData()) {
            onError(it)
        }
    }

    // on error received
    private fun onError(dto: ErrorDTO) {
        Timber.d("onError: $dto")
    }

    // check update
    private fun checkUpdate() {
        // Creates instance of the manager.
        appUpdateManager = AppUpdateManagerFactory.create(this@HomeActivity)

        // Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                // For a flexible update, use AppUpdateType.FLEXIBLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                // adding the listener to get the state updates
                appUpdateManager.registerListener(installUpdateListener)

                // Request the update.
                Timber.d("Update present, requesting update")
                appUpdateManager.startUpdateFlowForResult(
                    // Pass the intent that is returned by 'getAppUpdateInfo()'.
                    appUpdateInfo,
                    // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                    AppUpdateType.FLEXIBLE,
                    // The current activity making the update request.
                    this@HomeActivity,
                    // Include a request code to later monitor this update request.
                    REQUEST_CODE_UPDATE
                )
            } else {
                Timber.d("No update is present")
            }
        }
    }

    /* Displays the snack bar notification and call to action. */
    private fun popupSnackBarForCompleteUpdate() {
        // TODO: showing the snackbar
        appUpdateManager.completeUpdate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_UPDATE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    Timber.d("Update flow ok")
                }
                Activity.RESULT_CANCELED -> {
                    Timber.d("Update flow cancelled: $resultCode")
                }
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                    Timber.d("Update flow failed: $resultCode")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // When status updates are no longer needed, unregister the listener.
        appUpdateManager.unregisterListener(installUpdateListener)
    }

    override fun onMovieItemClicked(movie: MovieDTO) {
        // saving the movie details
        homeViewModel.saveMovieDetail(movieDTO = movie);

        // starting movie detail activity
        val intent = Intent(this@HomeActivity, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_ID, movie.id)
        startActivity(intent)
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.popularMoviesCardView -> {
                Timber.d("Popular movies clicked")
                val intent = Intent(this@HomeActivity, MoviesListActivity::class.java)
                intent.putExtra(SHOW_POPULAR_MOVIES, true)
                startActivity(intent)
            }
            R.id.trendingMoviesCardView -> {
                Timber.d("Trending movies clicked")
                val intent = Intent(this@HomeActivity, MoviesListActivity::class.java)
                intent.putExtra(SHOW_TRENDING_MOVIES, true)
                startActivity(intent)
            }
        }
    }
}

