package com.santra.sanchita.interviewprepapp.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.santra.sanchita.interviewprepapp.di.ApplicationContext;
import com.santra.sanchita.interviewprepapp.di.PreferenceInfo;
import com.santra.sanchita.interviewprepapp.utils.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by sanchita on 21/11/17.
 */

@Singleton
public class AppPreferenceHelper implements PreferenceHelper {
    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferenceHelper(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAuthCode() {
        return mPrefs.getString(Constants.SHARED_PREF_AUTH_CODE, null);
    }

    @Override
    public void setAuthCode(String authCode) {
        mPrefs.edit().putString(Constants.SHARED_PREF_AUTH_CODE, authCode).apply();
    }

    @Override
    public boolean isFirstRun() {
        return mPrefs.getBoolean(Constants.FIRST_RUN, true);
    }

    @Override
    public void setFirstRun(boolean firstRun) {
        mPrefs.edit().putBoolean(Constants.FIRST_RUN, firstRun).apply();
    }
}
