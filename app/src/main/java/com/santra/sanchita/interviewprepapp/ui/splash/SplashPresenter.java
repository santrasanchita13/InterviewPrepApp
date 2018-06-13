package com.santra.sanchita.interviewprepapp.ui.splash;

import com.santra.sanchita.interviewprepapp.data.DataManager;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.data.network.model.Question;
import com.santra.sanchita.interviewprepapp.ui.base.BasePresenter;
import com.santra.sanchita.interviewprepapp.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sanchita on 8/12/17.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void syncDatabase() {
        getCompositeDisposable().add(getDataManager()
                .getQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(networkQuestions -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    List<Question> questions = networkQuestions.getQuestions();

                    if(questions != null && questions.size() > 0) {
                        for(Question question : questions) {
                            InterviewItem interviewItem = new InterviewItem(question.getQuestion(), question.getAnswer(), false);
                            addToOfflineDb(interviewItem);
                        }
                    }
                    getMvpView().nextActivity();
                }, throwable -> {
                    if(!isViewAttached()) {
                        return;
                    }
                    getMvpView().errorSyncingDatabase();
                    getMvpView().nextActivity();
                }));
    }

    public void addToOfflineDb(InterviewItem question) {
        getCompositeDisposable().add(getDataManager()
                .addToOfflineDb(question)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(inserted -> {
                    if (!isViewAttached()) {
                        return;
                    }
                }, throwable -> {
                    if(!isViewAttached()) {
                        return;
                    }
                    getMvpView().errorSyncingDatabase();
                }));
    }
}
