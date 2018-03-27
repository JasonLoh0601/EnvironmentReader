package com.world.jasonloh95.evironmentreader;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
@SmallTest
public class EspressoTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);


    @Test
    public void clickButton() throws Exception {
        Thread.sleep(1000);
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(1000);
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        Thread.sleep(1000);

        // Start the screen of your activity.
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.psi));
        Thread.sleep(1000);
        // Check that you Activity was opened.
        String expectedNoStatisticsText = InstrumentationRegistry.getTargetContext()
                .getString(R.string.east);
        onView(withId(R.id.east)).check(matches(withText(expectedNoStatisticsText)));
        Thread.sleep(1000);
        onView(withId(R.id.button)).perform(click());

        Thread.sleep(1000);
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        Thread.sleep(1000);

        // Start the screen of your activity.
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.PM25));
        Thread.sleep(1000);
        // Check that you Activity was opened.
         expectedNoStatisticsText = InstrumentationRegistry.getTargetContext()
                .getString(R.string.national);
        onView(withId(R.id.national)).check(matches(withText(expectedNoStatisticsText)));

        Thread.sleep(1000);
        onView(withId(R.id.button)).perform(click());

        Thread.sleep(1000);
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        Thread.sleep(1000);

        // Start the screen of your activity.
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.home));

        Thread.sleep(1000);
        onView(withId(R.id.button)).perform(click());


    }
}
