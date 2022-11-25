package com.example.gorup16project;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class WelcomeEmployerTest {

    @Rule
    public ActivityScenarioRule<WelcomeActivityEmployer> myRule = new ActivityScenarioRule<>(WelcomeActivityEmployer.class);

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void testLogout(){
        init();
        onView(withId(R.id.Logout)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }
}
