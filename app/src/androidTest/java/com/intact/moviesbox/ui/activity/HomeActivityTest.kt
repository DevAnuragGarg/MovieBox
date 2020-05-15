package com.intact.moviesbox.ui.activity

import androidx.appcompat.widget.AppCompatTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.intact.moviesbox.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 *  This tells JUnit to run the tests inside of AndroidJUnit4 instead of the
 *  runner built into JUnit. AndroidJUnit4ClassRunner is a test runner that runs JUnit
 *  style tests on Android devices.
 *
 *  as we are using helper dependencies for android x we no need to write the activity rule.
 *  if not added need to write the rule
 *  @Rule val mActivityRule : ActivityTestRule<OrderActivity> = ActivityTestRule<>(OrderActivity.class)
 *  androidTestImplementation 'androidx.test:rules:1.2.0'
 *
 *  scroll to is not working for the recycler view or scroll view. In place perform an action
 *  to swipe up which works in most of the cases.
 *
 *  You can also use withText in place of withId which will help to check the strings
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest

@get:Rule
val activityScenario = ActivityScenarioRule(HomeActivity::class.java)

// here the test passes when the button is matched with not visible.
// When the view is outside the screen, it is not displayed, but it still exist in
// the view hierarchy. When you scroll down, only then it is visible. To solve this
// If at the bottom of the ScrollView you need to find a view and match something
// against it, then simply perform the scrollTo()/scroll down action on it, before any other
// actions that require it to be displayed.
@Test
fun checkNowPlayingLayoutVisible() {

    // Add a matcher for a view with the ID
    onView(withId(R.id.nowPlayingFrameLayout))
        // verify that the matched view is displayed on screen.
        .check(matches(isDisplayed()))
}

@Test
fun checkTopRatedLayoutVisible() {

    // Add a matcher for a view with the ID
    onView(withId(R.id.topRatedFrameLayout))
        // verify that the matched view is displayed on screen.
        .check(matches(isDisplayed()))
}

@Test
fun checkUpcomingLayoutVisible() {

    // Add a matcher for a view with the ID
    onView(withId(R.id.upcomingFrameLayout))
        // verify that the matched view is displayed on screen.
        .check(matches(isDisplayed()))
}

@Test
fun checkPopularMoviesButtonVisible() {

    // Add a matcher for a view with the text
    onView(withId(R.id.homeNestedScrollView))
        // swiping down to make the view visible
        .perform(swipeUp())

    // Add a matcher for a view with the ID
    onView(withId(R.id.tvPopularMovies))
        // verify that the matched view is displayed on screen.
        .check(matches(isDisplayed()))
}

@Test
fun checkTrendingMoviesButtonVisible() {

    // Add a matcher for a view with the text
    onView(withId(R.id.homeNestedScrollView))
        // swiping down to make the view visible
        .perform(swipeUp())

    // Add a matcher for a view with the ID
    onView(withId(R.id.tvTrendingMovies))
        // verify that the matched view is displayed on screen.
        .check(matches(isDisplayed()))
}

@Test
fun clickTrendingMoviesButton() {

    // Add a matcher for a view with the text
    onView(withId(R.id.homeNestedScrollView))
        // swiping down to make the view visible
        .perform(swipeUp())

    // perform click on the card view
    onView(withId(R.id.tvTrendingMovies))
        // swiping down to make the view visible
        .perform(click())

    //In case you have simple Toolbar without inner TextView.
    onView(allOf(instanceOf(AppCompatTextView::class.java), withParent(withId(R.id.toolbar))))
        .check(matches(withText(R.string.trending_movies_title)))
}

@Test
fun clickPopularMoviesButton() {

    // Add a matcher for a view with the text
    onView(withId(R.id.homeNestedScrollView))
        // swiping down to make the view visible
        .perform(swipeUp())

    // perform click on the card view
    onView(withId(R.id.tvPopularMovies))
        // swiping down to make the view visible
        .perform(click())

    //In case you have simple Toolbar without inner TextView.
    onView(allOf(instanceOf(AppCompatTextView::class.java), withParent(withId(R.id.toolbar))))
        .check(matches(withText(R.string.popular_movies_title)))
}