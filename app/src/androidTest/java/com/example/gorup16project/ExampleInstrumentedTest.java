package com.example.gorup16project;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);


//    @BeforeClass
//    public static void setup() {
//        Intents.init();
//    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }



    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.gorup16project", appContext.getPackageName());
    }

    @Test
    public void checkIfMainActivityPageIsVisible() {
        onView(withId(R.id.editTextTextEmailAddress3)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.editTextTextPassword3)).check(matches(withText(R.string.EMPTY_STRING)));
    }
    @Test
    public void checkIfPasswordIsVisible() {
        onView(withId(R.id.editTextTextPassword3)).perform(typeText("123"));
        onView(withId(R.id.checkBox)).perform(click());
        onView(withId(R.id.editTextTextPassword3)).check(matches(withText(R.string.PASSWORD)));

    }

//    @Test
//    public void checkIfMovedToCreateAccount() {
//        onView(withId(R.id.buttonCreateAccount)).perform(click());
//        intended(hasComponent(CreateAccount.class.getName()));
//    }

        @Test
    public void checkIfMovedToCreateAccount() {
        onView(withId(R.id.buttonCreateAccount)).perform(click());
            onView(withId(R.id.editTextTextEmailAddress2)).check(matches(withText(R.string.EMPTY_STRING)));
            onView(withId(R.id.editTextTextPersonName)).check(matches(withText(R.string.EMPTY_STRING)));
            onView(withId(R.id.editTextTextPassword)).check(matches(withText(R.string.EMPTY_STRING)));
            onView(withId(R.id.editTextTextPersonName3)).check(matches(withText(R.string.EMPTY_STRING)));
    }

}







