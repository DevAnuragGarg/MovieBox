package com.intact.moviesbox.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
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
import com.intact.moviesbox.presentation.viewmodels.FragmentListViewModel
import com.intact.moviesbox.ui.MoviesListActivity
import com.intact.moviesbox.ui.base.BaseActivity
import com.intact.moviesbox.ui.base.CustomViewModelFactory
import com.intact.moviesbox.ui.movieDetail.MovieListFragment
import com.intact.moviesbox.util.MovieListType
import com.intact.moviesbox.util.REQUEST_CODE_UPDATE
import com.intact.moviesbox.util.SHOW_POPULAR_MOVIES
import com.intact.moviesbox.util.SHOW_TRENDING_MOVIES
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    @NowPlayingQualifier
    lateinit var nowPlayingMoviesFragment: MovieListFragment

    @Inject
    @TopRatedQualifier
    lateinit var topRatedMoviesFragment: MovieListFragment

    @Inject
    @UpcomingQualifier
    lateinit var upcomingMoviesFragment: MovieListFragment

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var binding: ActivityHomeBinding              // view binding jet-pack
    private lateinit var fragmentListViewModel: FragmentListViewModel
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
        // set the action bar
        setSupportActionBar(binding.toolbar)

        // Add now playing fragment to the activity's container layout
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.nowPlayingFrameLayout, nowPlayingMoviesFragment,
            MovieListType.NowPlayingMovies.name
        )

        // Add top rated fragment to the activity's container layout
        fragmentTransaction.replace(
            R.id.topRatedFrameLayout, topRatedMoviesFragment,
            MovieListType.TopRatedMovies.name
        )

        // Add upcoming fragment to the activity's container layout
        fragmentTransaction.replace(
            R.id.upcomingFrameLayout, upcomingMoviesFragment,
            MovieListType.UpcomingMovies.name
        )

        // Commit the transaction
        fragmentTransaction.commit()

        // initializing app center for continuous integration
        AppCenter.start(
            application, "5a8030df-7ef1-4007-a0f7-807d8d6dd058",
            Analytics::class.java, Crashes::class.java
        )

        // check if there is a new update
        checkUpdate()
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

