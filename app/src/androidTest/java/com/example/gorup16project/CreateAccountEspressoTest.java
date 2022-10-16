package com.example.gorup16project;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CreateAccountEspressoTest {

    @Rule
    public ActivityScenarioRule<CreateAccount> myRule2 = new ActivityScenarioRule<>(CreateAccount.class);





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
    public void checkIfCreateAccountPageIsVisible() {
        onView(withId(R.id.editTextTextEmailAddress2)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.editTextTextPersonName)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.editTextTextPassword)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.editTextTextPersonName3)).check(matches(withText(R.string.EMPTY_STRING)));

    }
    @Test
    public void checkIfMovedToLoginPage() {
        onView(withId(R.id.backToLoginButton)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress3)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.editTextTextPassword3)).check(matches(withText(R.string.EMPTY_STRING)));
    }





}
