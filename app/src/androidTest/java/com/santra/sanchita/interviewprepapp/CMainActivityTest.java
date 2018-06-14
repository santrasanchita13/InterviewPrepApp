package com.santra.sanchita.interviewprepapp;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.santra.sanchita.interviewprepapp.ui.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by sanchita on 14/6/18.
 */

@RunWith(AndroidJUnit4.class)
public class CMainActivityTest {

    public CMainActivityTest() {
        super();
    }

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
    }

    @Test
    public void mainActivityDisplay() throws Exception {

        onView(withId(R.id.startPreparationButton)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.previousQuestionsButton)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.startPreparationButton)).perform(click());

        LowLevelActions.rotateScreen(mainActivityActivityTestRule.getActivity());

        onView(withId(R.id.startPreparationButton)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.previousQuestionsButton)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.startPreparationButton)).perform(click());
    }
}
