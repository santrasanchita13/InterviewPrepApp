package com.santra.sanchita.interviewprepapp.ui.splash;

import com.santra.sanchita.interviewprepapp.ui.base.MvpPresenter;

/**
 * Created by sanchita on 8/12/17.
 */

public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {

    void syncDatabase();
}
