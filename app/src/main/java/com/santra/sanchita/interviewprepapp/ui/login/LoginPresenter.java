package com.santra.sanchita.interviewprepapp.ui.login;

import com.santra.sanchita.interviewprepapp.data.DataManager;
import com.santra.sanchita.interviewprepapp.ui.base.BasePresenter;
import com.santra.sanchita.interviewprepapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sanchita on 13/6/18.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void saveOnlineAuthCode(String authCode) {
        getDataManager().setAuthCode(authCode);
        getMvpView().signUpSuccessful();
    }

    /*@Override
    public void getQuestionList() {
        getCompositeDisposable().add(getDataManager()
                .getInterviewQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(interviewItems -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    if(interviewItems != null && interviewItems.size() > 0) {
                        for (InterviewItem interviewItem : interviewItems) {
                            getMvpView().addToList(interviewItem);
                        }
                    }
                }, throwable -> {
                    if(!isViewAttached()) {
                        return;
                    }
                    getMvpView().onError(R.string.default_error);
                }));
    }*/
}
