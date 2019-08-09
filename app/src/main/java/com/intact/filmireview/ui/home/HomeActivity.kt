package com.intact.filmireview.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.intact.filmireview.R
import com.intact.filmireview.data.model.ErrorDTO
import com.intact.filmireview.extension.observeLiveData
import com.intact.filmireview.ui.BaseActivity
import com.intact.filmireview.ui.CustomViewModelFactory
import com.intact.filmireview.util.REQUEST_CODE_UPDATE
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolBar: Toolbar

    @BindView(R.id.popularRecyclerView)
    lateinit var popularRecyclerView: RecyclerView

    @BindView(R.id.topRatedMoviesRecyclerView)
    lateinit var topRatedMoviesRecyclerView: RecyclerView

    @Inject
    lateinit var popularMoviesAdapter: BaseMoviesAdapter

    @Inject
    lateinit var topRatedMoviesAdapter: BaseMoviesAdapter

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var appUpdateManager: AppUpdateManager
    private val installUpdateListener: InstallStateUpdatedListener = InstallStateUpdatedListener { state ->
        if (state.installStatus() == InstallStatus.DOWNLOADED) {
            Timber.d("Download update is complete")
            popupSnackbarForCompleteUpdate()
        } else {
            Timber.d("Download status: ${state.installStatus()} and Error code: ${state.installErrorCode()}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initializeVariables()
    }

    private fun initializeVariables() {

        // check if there is a new update
        checkUpdate()

        // initialize butterknife
        ButterKnife.bind(this)

        // set the action bar
        setSupportActionBar(toolBar)

        // initializing app center for continous integration
        AppCenter.start(
            application, "5a8030df-7ef1-4007-a0f7-807d8d6dd058",
            Analytics::class.java, Crashes::class.java
        )

        // update empty UI
        updatePopularMoviesUI()
        updatedTopRatedMoviesUI()

        // get the view model
        val homeViewModel = ViewModelProviders.of(this@HomeActivity, viewModelFactory).get(HomeViewModel::class.java)
        setObservers(homeViewModel)
        homeViewModel.getPopularMovies()
        homeViewModel.getTopRatedMovies()
    }

    // Checks that the update is not stalled during 'onResume()'.
    // However, you should execute this check at all app entry points.
    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackbarForCompleteUpdate()
            }
        }
    }

    // updating the popular movies UI
    private fun updatePopularMoviesUI() {
        popularMoviesAdapter.setMoviesData(ArrayList())

        with(popularRecyclerView) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularMoviesAdapter
            popularMoviesAdapter.notifyDataSetChanged()
        }
    }

    // updating the top rated movies UI
    private fun updatedTopRatedMoviesUI() {
        topRatedMoviesAdapter.setMoviesData(ArrayList())

        with(topRatedMoviesRecyclerView) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedMoviesAdapter
            topRatedMoviesAdapter.notifyDataSetChanged()
        }
    }

    // setting the observers
    private fun setObservers(viewModel: HomeViewModel) {
        observeLiveData(viewModel.getPopularMoviesLiveData()) {
            Timber.d("Updating popular movies data")
            popularMoviesAdapter.setMoviesData(it)
        }
        observeLiveData(viewModel.getTopRatedMoviesLiveData()) {
            Timber.d("Updating top rated movies data")
            topRatedMoviesAdapter.setMoviesData(it)
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

    /* Displays the snackbar notification and call to action. */
    private fun popupSnackbarForCompleteUpdate() {
        // TODO: showing the snackbar
        appUpdateManager.completeUpdate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_UPDATE) {
            if (resultCode == Activity.RESULT_OK) {
                Timber.d("Update flow ok")
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Timber.d("Update flow cancelled: $resultCode")
            } else if (resultCode == ActivityResult.RESULT_IN_APP_UPDATE_FAILED) {
                Timber.d("Update flow failed: $resultCode")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // When status updates are no longer needed, unregister the listener.
        appUpdateManager.unregisterListener(installUpdateListener);
    }
}

