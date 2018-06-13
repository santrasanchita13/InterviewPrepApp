package com.santra.sanchita.interviewprepapp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.santra.sanchita.interviewprepapp.R;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sanchita on 13/6/18.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> presenter;

    @BindView(R.id.loginTextView)
    TextView loginTextView;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        loginTextView.setText("");
        presenter.getQuestionList();
    }

    @Override
    public void addToList(InterviewItem interviewItem) {
        String question = "Question: " + interviewItem.getQuestion()
                + "\nAnswer: " + interviewItem.getAnswer()
                +"\n\n";
        loginTextView.append(question);
    }
}
