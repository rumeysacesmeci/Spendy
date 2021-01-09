package com.example.spendy.ui.signIn


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.spendy.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SignInBudgetManagerActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SignInActivity::class.java)

    @Test
    fun signInBudgetManagerActivityTest() {
        val textInputEditText = onView(
            allOf(
                withId(R.id.txtEmail),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.filledEmail),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("korkmaz@gmail.com"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.txtPassword),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.filledTextField),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("123456"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.btnSignIn), withText("Sign In"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val appCompatImageButton = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val navigationMenuItemView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.navigationView),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.txtAmount),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentHolder),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("90"), closeSoftKeyboard())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.btnIncome), withText("INCOME"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentHolder),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.rvCategories),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(3, click()))
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
}
