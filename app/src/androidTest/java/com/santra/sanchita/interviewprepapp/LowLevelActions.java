package com.santra.sanchita.interviewprepapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.MotionEvents;
import android.support.test.espresso.action.Press;
import android.view.MotionEvent;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.concurrent.CountDownLatch;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;

/**
 * Created by sanchita on 29/12/17.
 */

public class LowLevelActions {
    static MotionEvent sMotionEventDownHeldView = null;

    public static PressAndHoldAction pressAndHold() {
        return new PressAndHoldAction();
    }

    public static ReleaseAction release() {
        return new ReleaseAction();
    }

    public static void tearDown() {
        sMotionEventDownHeldView = null;
    }

    static class PressAndHoldAction implements ViewAction {
        @Override
        public Matcher<View> getConstraints() {
            return isDisplayingAtLeast(90); // Like GeneralClickAction
        }

        @Override
        public String getDescription() {
            return "Press and hold action";
        }

        @Override
        public void perform(final UiController uiController, final View view) {
            if (sMotionEventDownHeldView != null) {
                throw new AssertionError("Only one view can be held at a time");
            }

            float[] precision = Press.FINGER.describePrecision();
            float[] coords = GeneralLocation.CENTER.calculateCoordinates(view);
            sMotionEventDownHeldView = MotionEvents.sendDown(uiController, coords, precision).down;
            // TODO: save view information and make sure release() is on same view
        }
    }

    static class ReleaseAction implements ViewAction {
        @Override
        public Matcher<View> getConstraints() {

            // Like GeneralClickAction
            return isDisplayingAtLeast(90);
        }

        @Override
        public String getDescription() {
            return "Release action";
        }

        @Override
        public void perform(final UiController uiController, final View view) {
            if (sMotionEventDownHeldView == null) {
                throw new AssertionError("Before calling release(), you must call pressAndHold() on a view");
            }

            float[] coords = GeneralLocation.CENTER.calculateCoordinates(view);
            MotionEvents.sendUp(uiController, sMotionEventDownHeldView, coords);
        }
    }

    public static void rotateScreen(Activity activity) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final int orientation = InstrumentationRegistry.getTargetContext()
                .getResources()
                .getConfiguration()
                .orientation;
        final int newOrientation = (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        activity.setRequestedOrientation(newOrientation);

        getInstrumentation().waitForIdle(new Runnable() {
            @Override
            public void run() {
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Screen rotation failed", e);
        }
    }
}