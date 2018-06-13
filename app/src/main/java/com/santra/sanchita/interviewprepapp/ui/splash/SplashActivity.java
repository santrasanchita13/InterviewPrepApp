package com.santra.sanchita.interviewprepapp.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.santra.sanchita.interviewprepapp.R;
import com.santra.sanchita.interviewprepapp.ui.base.BaseActivity;
import com.santra.sanchita.interviewprepapp.ui.login.LoginActivity;
import com.santra.sanchita.interviewprepapp.utils.Constants;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by sanchita on 8/12/17.
 */

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> presenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        hideActionBar();

        setUp();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();

        super.onDestroy();
    }

    @Override
    protected void setUp() {
        new Handler().postDelayed(() -> presenter.syncDatabase(), Constants.SPLASH_DELAY);
    }

    @Override
    public void nextActivity() {
        startActivity(LoginActivity.getStartIntent(this));
    }

    @Override
    public void errorSyncingDatabase() {
        onError(R.string.default_error);
        finish();
    }
}
