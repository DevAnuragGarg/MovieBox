package com.intact.moviesbox.ui.activity

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.ConfigurationCompat
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
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
import com.intact.moviesbox.ui.base.BaseActivity
import com.intact.moviesbox.ui.fragment.MovieListFragment
import com.intact.moviesbox.util.INTENT_KEY_SHOW_POPULAR_MOVIES
import com.intact.moviesbox.util.INTENT_KEY_SHOW_TRENDING_MOVIES
import com.intact.moviesbox.util.MovieListType
import com.intact.moviesbox.util.REQUEST_CODE_UPDATE
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import timber.log.Timber
import javax.inject.Inject

/**
 *  OnNavigationItemSelectedListener: interface offers the onNavigationItemSelected() method, which
 *  is called when an item in the navigation drawer menu item is tapped.
 */
class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    @NowPlayingQualifier
    lateinit var nowPlayingMoviesFragment: MovieListFragment

    @Inject
    @TopRatedQualifier
    lateinit var topRatedMoviesFragment: MovieListFragment

    @Inject
    @UpcomingQualifier
    lateinit var upcomingMoviesFragment: MovieListFragment

    private lateinit var binding: ActivityHomeBinding              // view binding jet-pack
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
        supportActionBar!!.setDisplayShowTitleEnabled(false);

        // get the local language
        // TODO: Make api calls as per the language
        val language =
            ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0]?.language
        Timber.d("Language selected: $language")

        // show if dark theme is enabled or not
        showDarkThemeEnabled()

        // set the drawer layout with toggle. need to pass on the toolbar
        // so that hamburger menu gets drawn on the toolbar not on action bar
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.action_bar_toggle_open,
            R.string.action_bar_toggle_close
        )

        //Setting the actionbarToggle to drawer layout
        binding.drawerLayout.addDrawerListener(toggle)

        //calling sync state is necessary or else your hamburger icon wont show up
        toggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener(this)

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
                intent.putExtra(INTENT_KEY_SHOW_POPULAR_MOVIES, true)
                startActivity(intent)
            }

            R.id.trendingMoviesCardView -> {
                Timber.d("Trending movies clicked")
                val intent = Intent(this@HomeActivity, MoviesListActivity::class.java)
                intent.putExtra(INTENT_KEY_SHOW_TRENDING_MOVIES, true)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.dark_mode_selection) {
            if (item.title == getString(R.string.dark_mode_off)) {
                item.title = getString(R.string.dark_mode_on)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                item.title = getString(R.string.dark_mode_off)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                // Noting has to be done as we are on the same activity
            }

            R.id.nav_popular_movies -> {
                val intent = Intent(this@HomeActivity, MoviesListActivity::class.java)
                intent.putExtra(INTENT_KEY_SHOW_POPULAR_MOVIES, true)
                startActivity(intent)
            }

            R.id.nav_trending_movies -> {
                val intent = Intent(this@HomeActivity, MoviesListActivity::class.java)
                intent.putExtra(INTENT_KEY_SHOW_TRENDING_MOVIES, true)
                startActivity(intent)
            }

            R.id.nav_share -> {
                // TODO
            }

            R.id.nav_about_us -> {
                // TODO
            }
        }
        //Closing drawer on item click
        binding.drawerLayout.closeDrawer(GravityCompat.START);

        // return true for each case
        return true
    }
}

