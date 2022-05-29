package com.suki.thewheel.presentation.entries

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.suki.thewheel.R
import com.suki.thewheel.di.AppModule
import com.suki.thewheel.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
@UninstallModules(AppModule::class)
class EntryListFragmentUITest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun emptyListMessageIsDisplayed() {
        val textView = onView(
            Matchers.allOf(
                withId(R.id.empty_message),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Oops! The list is empty.\n" +
                " Start by adding new entries…")))
    }

    @Test
    fun editTextForEntryInputHintIsDisplayed() {
        onView(withHint("Add entry"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun addButtonIsDisplayed() {
        val button = onView(
            Matchers.allOf(
                withId(R.id.addButton),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))
    }

    @Test
    fun letsGoButtonIsDisplayedAndDisabled() {
        val button = onView(
            Matchers.allOf(
                withId(R.id.action_lets_go), withText("LET'S GO"),
                withParent(withParent(withId(androidx.appcompat.R.id.action_bar))),
                isDisplayed()
            )
        )
        button.check(matches(withText("LET'S GO")))
        button.check(matches(isNotEnabled()))
    }


}