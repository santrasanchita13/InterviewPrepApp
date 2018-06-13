package com.santra.sanchita.interviewprepapp.data.prefs;

/**
 * Created by sanchita on 21/11/17.
 */

public interface PreferenceHelper {
    String getAuthCode();

    void setAuthCode(String authCode);

    boolean isFirstRun();

    void setFirstRun(boolean firstRun);
}