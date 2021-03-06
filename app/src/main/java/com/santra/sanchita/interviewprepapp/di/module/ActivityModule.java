package com.santra.sanchita.interviewprepapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.santra.sanchita.interviewprepapp.di.ActivityContext;
import com.santra.sanchita.interviewprepapp.di.PerActivity;
import com.santra.sanchita.interviewprepapp.ui.login.LoginMvpPresenter;
import com.santra.sanchita.interviewprepapp.ui.login.LoginMvpView;
import com.santra.sanchita.interviewprepapp.ui.login.LoginPresenter;
import com.santra.sanchita.interviewprepapp.ui.main.MainMvpPresenter;
import com.santra.sanchita.interviewprepapp.ui.main.MainMvpView;
import com.santra.sanchita.interviewprepapp.ui.main.MainPresenter;
import com.santra.sanchita.interviewprepapp.ui.question.QuestionMvpPresenter;
import com.santra.sanchita.interviewprepapp.ui.question.QuestionMvpView;
import com.santra.sanchita.interviewprepapp.ui.question.QuestionPresenter;
import com.santra.sanchita.interviewprepapp.ui.revise.ReviseMvpPresenter;
import com.santra.sanchita.interviewprepapp.ui.revise.ReviseMvpView;
import com.santra.sanchita.interviewprepapp.ui.revise.RevisePresenter;
import com.santra.sanchita.interviewprepapp.ui.splash.SplashMvpPresenter;
import com.santra.sanchita.interviewprepapp.ui.splash.SplashMvpView;
import com.santra.sanchita.interviewprepapp.ui.splash.SplashPresenter;
import com.santra.sanchita.interviewprepapp.utils.rx.AppSchedulerProvider;
import com.santra.sanchita.interviewprepapp.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sanchita on 6/12/17.
 */

@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    QuestionMvpPresenter<QuestionMvpView> provideQuestionPresenter(QuestionPresenter<QuestionMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ReviseMvpPresenter<ReviseMvpView> provideRevisePresenter(RevisePresenter<ReviseMvpView> presenter) {
        return presenter;
    }
}
