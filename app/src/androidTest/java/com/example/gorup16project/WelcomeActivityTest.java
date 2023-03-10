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
public class WelcomeActivityTest {

    @Rule
    public ActivityScenarioRule<WelcomeActivity> myRule = new ActivityScenarioRule<>(WelcomeActivity.class);

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

    @Test
    public void testProfileBtn(){
        init();
        onView(withId(R.id.profileBtn)).perform(click());
        intended(hasComponent(ProfilePage.class.getName()));

    }

    @Test
    public void setPreferenceBtn(){
        init();
        onView(withId(R.id.setPreferences)).perform(click());
        intended(hasComponent(SetPreferencesEmployee.class.getName()));

    }
}
