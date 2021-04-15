package com.example.alias.activities

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

//@RunWith(AndroidJUnit4::class)
//@LargeTest
//class MainMenuActivityTest {
//    private lateinit var stringToBetyped: String
//
//    @get:Rule
//    var activityRule: ActivityScenarioRule<MainMenuActivity>
//            = ActivityScenarioRule(MainActivity::class.java)
//
//    @Before
//    fun initValidString() {
//        // Specify a valid string.
//        stringToBetyped = "Espresso"
//    }
//
//    @Test
//    fun changeText_sameActivity() {
//        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput))
//            .perform(typeText(stringToBetyped), closeSoftKeyboard())
//        onView(withId(R.id.changeTextBt)).perform(click())
//
//        // Check that the text was changed.
//        onView(withId(R.id.textToBeChanged))
//            .check(matches(withText(stringToBetyped)))
//    }
//    @Test
//    fun `test 1`() {
//        onView(withId(R.id.my_view))            // withId(R.id.my_view) is a ViewMatcher
//            .perform(click())               // click() is a ViewAction
//            .check(matches(isDisplayed()))  // matches(isDisplayed()) is a ViewAssertion
//    }
//}