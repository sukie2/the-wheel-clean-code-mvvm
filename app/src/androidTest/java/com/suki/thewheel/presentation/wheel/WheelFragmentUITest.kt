package com.suki.thewheel.presentation.wheel


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.suki.thewheel.R
import com.suki.thewheel.presentation.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class WheelFragmentUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun inputData() {
        val textInputEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editTextEntry),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.entryInputLayout),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textInputEditText.perform(
            ViewActions.replaceText("sam"),
            ViewActions.closeSoftKeyboard()
        )

        val materialButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.addButton), ViewMatchers.withText("Add"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.nav_host_fragment),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        materialButton.perform(ViewActions.click())

        val textInputEditText2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editTextEntry),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.entryInputLayout),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textInputEditText2.perform(
            ViewActions.replaceText("tom"),
            ViewActions.closeSoftKeyboard()
        )

        val materialButton2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.addButton), ViewMatchers.withText("Add"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.nav_host_fragment),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        materialButton2.perform(ViewActions.click())

        val actionMenuItemView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.action_lets_go), ViewMatchers.withText("Let's go"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(androidx.appcompat.R.id.action_bar),
                        1
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        actionMenuItemView.perform(ViewActions.click())

    }


    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    @Test
    fun buttonSpinItIsDisplayed() {
        val button = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.buttonSpin),
                ViewMatchers.withParent(ViewMatchers.withParent(ViewMatchers.withId(R.id.nav_host_fragment))),
                ViewMatchers.isDisplayed()
            )
        )
        button.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        button.perform(ViewActions.click())
    }

    @Test
    fun buttonEditEntriesIsDisplayed() {
        val textView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.action_manage),
                ViewMatchers.withParent(ViewMatchers.withParent(ViewMatchers.withId(androidx.appcompat.R.id.action_bar))),
                ViewMatchers.isDisplayed()
            )
        )
        textView.check(ViewAssertions.matches(ViewMatchers.withText("EDIT ENTRIES")))
    }

    @Test
    fun wheelIsDisplayed() {
        val view = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.wheel),
                ViewMatchers.withParent(ViewMatchers.withParent(ViewMatchers.withId(R.id.nav_host_fragment))),
                ViewMatchers.isDisplayed()
            )
        )
        view.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}

