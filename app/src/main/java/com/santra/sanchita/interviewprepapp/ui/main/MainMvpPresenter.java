package com.santra.sanchita.interviewprepapp.ui.main;

import com.santra.sanchita.interviewprepapp.ui.base.MvpPresenter;

/**
 * Created by sanchita on 13/6/18.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void setFirstRun(boolean firstRun);

    boolean isFirstRun();
}
