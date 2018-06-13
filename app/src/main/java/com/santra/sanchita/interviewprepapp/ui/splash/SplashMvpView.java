package com.santra.sanchita.interviewprepapp.ui.splash;

import com.santra.sanchita.interviewprepapp.ui.base.MvpView;

/**
 * Created by sanchita on 8/12/17.
 */

public interface SplashMvpView extends MvpView {

    void errorSyncingDatabase();

    void nextActivity();
}
