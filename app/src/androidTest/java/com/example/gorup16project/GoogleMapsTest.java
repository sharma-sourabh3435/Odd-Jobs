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

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;



import org.hamcrest.Matcher;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(AndroidJUnit4.class)
public class GoogleMapsTest {

    @Rule
    public ActivityScenarioRule<MapsActivity> myRule = new ActivityScenarioRule<>(MapsActivity.class);

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void checkingLongitude() {
        // unit test to check longitude
        MapsActivity map = new MapsActivity();
        assertNotEquals(-300.0, map.getLongitude());

    }

    @Test
    public void checkingLatitude() {
        // unit test to check longitude
        MapsActivity map = new MapsActivity();
        assertNotEquals(-300.0, map.getLongitude());

    }


}