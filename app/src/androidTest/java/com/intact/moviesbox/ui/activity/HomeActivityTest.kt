package com.intact.moviesbox.ui.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.intact.moviesbox.R
import org.junit.Test
import org.junit.runner.RunWith

/**
 *  This tells JUnit to run the tests inside of AndroidJUnit4 instead of the
 *  runner built into JUnit. AndroidJUnit4ClassRunner is a test runner that runs JUnit
 *  style tests on Android devices.
 *
 *  as we are using helper dependencies for android x we no need to write the activity rule.
 *
 *  scroll to is not working for the recycler view or scroll view. In place perform an action
 *  to swipe up which works in most of the cases.
 *
 *  You can also use withText in place of withId which will help to check the strings
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    @Test
    fun activityLaunchSuccessful() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    // here the test passes when the button is matched with not visible.
    // When the view is outside the screen, it is not displayed, but it still exist in
    // the view hierarchy. When you scroll down, only then it is visible. To solve this
    // If at the bottom of the ScrollView you need to find a view and match something
    // against it, then simply perform the scrollTo() action on it, before any other
    // actions that require it to be displayed.

    @Test
    fun checkNowPlayingLayoutVisible() {
        // Launch the activity, same as before.
        ActivityScenario.launch(HomeActivity::class.java)

        // Add a matcher for a view with the ID
        onView(withId(R.id.nowPlayingFrameLayout))
            // verify that the matched view is displayed on screen.
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkTopRatedLayoutVisible() {
        // Launch the activity, same as before.
        ActivityScenario.launch(HomeActivity::class.java)

        // Add a matcher for a view with the ID
        onView(withId(R.id.topRatedFrameLayout))
            // verify that the matched view is displayed on screen.
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkUpcomingLayoutVisible() {
        // Launch the activity, same as before.
        ActivityScenario.launch(HomeActivity::class.java)

        // Add a matcher for a view with the ID
        onView(withId(R.id.upcomingFrameLayout))
            // verify that the matched view is displayed on screen.
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkPopularMoviesButtonVisible() {
        // Launch the activity, same as before.
        ActivityScenario.launch(HomeActivity::class.java)

        // Add a matcher for a view with the text
        onView(withId(R.id.homeNestedScrollView))
            // swiping down to make the view visible
            .perform(ViewActions.swipeUp())

        // Add a matcher for a view with the ID
        onView(withId(R.id.tvPopularMovies))
            // verify that the matched view is displayed on screen.
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkTrendingMoviesButtonVisible() {
        // Launch the activity, same as before.
        ActivityScenario.launch(HomeActivity::class.java)

        // Add a matcher for a view with the text
        onView(withId(R.id.homeNestedScrollView))
            // swiping down to make the view visible
            .perform(ViewActions.swipeUp())

        // Add a matcher for a view with the ID
        onView(withId(R.id.tvTrendingMovies))
            // verify that the matched view is displayed on screen.
            .check(matches(isDisplayed()))
    }
}