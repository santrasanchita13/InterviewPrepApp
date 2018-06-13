package com.santra.sanchita.interviewprepapp.ui.main;

import com.santra.sanchita.interviewprepapp.data.DataManager;
import com.santra.sanchita.interviewprepapp.ui.base.BasePresenter;
import com.santra.sanchita.interviewprepapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sanchita on 13/6/18.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void setFirstRun(boolean firstRun) {
        getDataManager().setFirstRun(firstRun);
    }

    @Override
    public boolean isFirstRun() {
        return getDataManager().isFirstRun();
    }
}
