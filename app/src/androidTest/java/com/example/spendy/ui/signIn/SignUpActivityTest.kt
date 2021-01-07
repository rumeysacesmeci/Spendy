package com.example.spendy.ui.signIn


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.spendy.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SignUpActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SignInActivity::class.java)

    @Test
    fun signUpActivityTest() {
        val appCompatTextView = onView(
            allOf(
                withId(R.id.tvSignUp), withText("Sign Up with E-Mail?"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.txtName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.txtLName),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("tes"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.txtSurname),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.txtLSurname),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("deneme"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.txtName), withText("tes"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.txtLName),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("test"))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.txtName), withText("test"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.txtLName),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(closeSoftKeyboard())

        val textInputEditText = onView(
            allOf(
                withId(R.id.txtEmail),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.txtLEmail),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("deneme@gmail.com"), closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.txtPassword),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.txtLPassword),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("123456"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.txtPasswordConfirm),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.txtLPasswordConfirm),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(replaceText("123456"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.btnLogin), withText("Sign Up"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())
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
