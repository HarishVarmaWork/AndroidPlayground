package com.example.androidplayground.anotherDaggerHiltDemo.ui

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidplayground.R
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DaggerHiltActivityTest : TestCase() {

    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {

    }

    @get:Rule
    val mActivityTestRule: ActivityScenarioRule<DaggerHiltActivity> =
        ActivityScenarioRule(DaggerHiltActivity::class.java)

    @Test
    fun someTest() {
        val scenario = launchActivity<DaggerHiltActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.loader))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.loader))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))

        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        /*    Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerViewAdapter.MyViewHolder>(
                        4,
                        click()
                    )
                )

            Espresso.onView(ViewMatchers.withText("Clicked on"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))*/


        //val recyclerView: RecyclerView = mActivityTestRule.
        //int itemCount = recyclerView.getAdapter().getItemCount();
    }
}