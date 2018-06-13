package com.santra.sanchita.interviewprepapp.ui.question;

import com.santra.sanchita.interviewprepapp.R;
import com.santra.sanchita.interviewprepapp.data.DataManager;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.ui.base.BasePresenter;
import com.santra.sanchita.interviewprepapp.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sanchita on 13/6/18.
 */

public class QuestionPresenter<V extends QuestionMvpView> extends BasePresenter<V> implements QuestionMvpPresenter<V> {

    @Inject
    public QuestionPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public List<InterviewItem> getQuestion() {
        getCompositeDisposable().add(getDataManager()
                .getUnsolvedQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(questionList -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    if(questionList != null && questionList.size() > 0) {
                        getMvpView().questionFetched(questionList.get(0));
                    }
                    else {
                        getMvpView().questionListEmpty();
                    }
                }, throwable -> {
                    if(!isViewAttached()) {
                        return;
                    }
                    getMvpView().onError(R.string.default_error);
                }));
        return null;
    }

    @Override
    public Boolean updateAnswer(InterviewItem interviewItem) {
        getCompositeDisposable().add(getDataManager()
                .updateAnswer(interviewItem)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(updated -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    if(updated) {
                        getMvpView().submittedAnswer();
                    }
                }, throwable -> {
                    if(!isViewAttached()) {
                        return;
                    }
                    getMvpView().onError(R.string.default_error);
                }));
        return true;
    }
}
