package com.santra.sanchita.interviewprepapp.utils;

/**
 * Created by sanchita on 6/12/17.
 */

public class AppLogger {
    public static void init() {
        /*if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }*/
    }

    public static void d(String s, Object... objects) {
        /*Timber.d(s, objects);*/
    }

    public static void i(String s, Object... objects) {
        /*Timber.i(s, objects);*/
    }

    public static void i(Throwable throwable, String s, Object... objects) {
        /*Timber.i(throwable, s, objects);*/
    }
}
