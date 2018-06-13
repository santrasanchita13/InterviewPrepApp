package com.santra.sanchita.interviewprepapp.ui.login;

import com.santra.sanchita.interviewprepapp.ui.base.MvpPresenter;

/**
 * Created by sanchita on 13/6/18.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {
    void saveOnlineAuthCode(String authCode);
}
