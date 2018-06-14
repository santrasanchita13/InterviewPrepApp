package com.santra.sanchita.interviewprepapp.ui.question;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.santra.sanchita.interviewprepapp.R;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.ui.base.BaseActivity;
import com.santra.sanchita.interviewprepapp.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sanchita on 13/6/18.
 */

public class QuestionActivity extends BaseActivity implements QuestionMvpView {

    @Inject
    QuestionMvpPresenter<QuestionMvpView> presenter;

    @BindView(R.id.questionTextView)
    TextView questionTextView;

    @BindView(R.id.answerEditText)
    EditText answerEditText;

    @BindView(R.id.constraintLayoutQuestion)
    ConstraintLayout constraintLayoutQuestion;

    InterviewItem presentQuestion = null;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, QuestionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

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
        if(getIntent().getBooleanExtra(Constants.REVISION, false)) {
            InterviewItem interviewItem = new InterviewItem(getIntent().getStringExtra(Constants.REVISING_QUESTION),
                    getIntent().getStringExtra(Constants.REVISING_ANSWER),
                    getIntent().getStringExtra(Constants.REVISING_USER_ANSWER), true);
            questionFetched(interviewItem);
        }
        else {
            presenter.getQuestion();
        }
    }

    @Override
    public void questionFetched(InterviewItem question) {
        presentQuestion = question;
        if(getIntent().getBooleanExtra(Constants.REVISION, false)) {
            answerEditText.setText(getIntent().getStringExtra(Constants.REVISING_USER_ANSWER));
        }
        else {
            answerEditText.setText("");
        }

        questionTextView.setText(question.getQuestion());
    }

    @Override
    public void questionListEmpty() {
        showfinishedPopUp();
    }

    @OnClick(R.id.questionActivityAd)
    public void questionActivityAdClick() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://courses.learncodeonline.in"));
        startActivity(intent);
    }

    @OnClick(R.id.submitButton)
    public void submitButtonClick() {
        if(answerEditText.getText() != null) {
            if(answerEditText.getText().toString().isEmpty()) {
                answerEditText.setError(getText(R.string.no_answer_error));
                answerEditText.requestFocus();
            }
            else {
                presentQuestion.setUserAnswer(answerEditText.getText().toString());
                presentQuestion.setSolved(true);
                presenter.updateAnswer(presentQuestion);
            }
        }
    }

    @Override
    public void submittedAnswer() {
        showPopUp();
    }

    public void showPopUp() {

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(answerEditText.getWindowToken(), 0);
        }

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);

        if(inflater != null) {
            View popupView = inflater.inflate(R.layout.pop_up_answer, null);

            TextView answerTextView = popupView.findViewById(R.id.answerTextView);

            answerTextView.setText(presentQuestion.getAnswer());

            int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
            int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true; // lets taps outside the popup also dismiss it
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            popupWindow.setAnimationStyle(R.style.SlideAnimation);

            // show the popup window
            popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);

            popupWindow.setOnDismissListener(() -> {
                if(getIntent().getBooleanExtra(Constants.REVISION, false)) {
                    QuestionActivity.this.finish();
                }
                else {
                    presenter.getQuestion();
                }
            });
        }
    }

    public void showfinishedPopUp() {

        constraintLayoutQuestion.setVisibility(View.GONE);

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(answerEditText.getWindowToken(), 0);
        }

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);

        if(inflater != null) {
            View popupView = inflater.inflate(R.layout.pop_up_no_more_questions, null);

            int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
            int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true; // lets taps outside the popup also dismiss it
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            popupWindow.setAnimationStyle(R.style.SlideAnimation);

            // show the popup window
            popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);

            popupWindow.setOnDismissListener(QuestionActivity.this::finish);
        }
    }
}
