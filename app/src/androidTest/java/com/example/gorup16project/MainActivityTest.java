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
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void checkIfRegistrationPageIsVisible() {
        onView(withId(R.id.editTextTextEmailAddress3)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.editTextTextPassword3)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    @Test
    public void checkIfPasswordIsVisible() {
        onView(withId(R.id.editTextTextPassword3)).perform(typeText("123"));
        onView(withId(R.id.checkBox)).perform(click());
        onView(withId(R.id.editTextTextPassword3)).check(matches(withText(R.string.PASSWORD)));

    }


    @Test
    public void checkIfCreateAccountButtonWorks() {
        init();
        onView(withId(R.id.buttonCreateAccount)).perform(click());
        intended(hasComponent(CreateAccount.class.getName()));
    }

}






