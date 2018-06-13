package com.santra.sanchita.interviewprepapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.santra.sanchita.interviewprepapp.R;
import com.santra.sanchita.interviewprepapp.ui.base.BaseActivity;
import com.santra.sanchita.interviewprepapp.ui.question.QuestionActivity;
import com.santra.sanchita.interviewprepapp.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sanchita on 13/6/18.
 */

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    @BindView(R.id.welcomeButton)
    TextView welcomeButton;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        welcomeButton.setText("Welcome " + getIntent().getStringExtra(Constants.LOGGED_IN_USER_NAME));
    }

    @OnClick(R.id.logoImageMain)
    public void logoImageClick() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://courses.learncodeonline.in"));
        startActivity(intent);
    }

    @OnClick(R.id.mainActivityAd)
    public void mainActivityAdClick() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://courses.learncodeonline.in"));
        startActivity(intent);
    }

    @OnClick(R.id.startPreparationButton)
    public void startPreparationButtonClick() {
        startActivity(QuestionActivity.getStartIntent(this));
    }

    @OnClick(R.id.previousQuestionsButton)
    public void previousQuestionsButtonClick() {

    }
}
