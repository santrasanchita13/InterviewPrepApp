package com.santra.sanchita.interviewprepapp;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.santra.sanchita.interviewprepapp.ui.splash.SplashActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by sanchita on 14/6/18.
 */

@RunWith(AndroidJUnit4.class)
public class ASplashActivityTest {

    public ASplashActivityTest() {
        super();
    }

    @Rule
    public ActivityTestRule<SplashActivity> splashActivityActivityTestRule =
            new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void splashActivityDisplay() throws Exception {

        onView(withId(R.id.imageLogoLauncher)).check(matches(isDisplayed()));

        LowLevelActions.rotateScreen(splashActivityActivityTestRule.getActivity());

        onView(withId(R.id.imageLogoLauncher)).check(matches(isDisplayed()));
    }
}
