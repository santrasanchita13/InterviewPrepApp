package com.santra.sanchita.interviewprepapp.ui.revise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.santra.sanchita.interviewprepapp.R;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.ui.base.BaseActivity;
import com.santra.sanchita.interviewprepapp.ui.question.QuestionActivity;
import com.santra.sanchita.interviewprepapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sanchita on 13/6/18.
 */

public class ReviseActivity extends BaseActivity implements ReviseMvpView {

    @Inject
    ReviseMvpPresenter<ReviseMvpView> presenter;

    @BindView(R.id.reviseRecyclerView)
    RecyclerView reviseRecyclerView;

    @BindView(R.id.sadIconTextView)
    TextView sadIconTextView;

    @BindView(R.id.nothingSolvedTextView)
    TextView nothingSolvedTextView;

    ReviseAdapter reviseAdapter;

    List<InterviewItem> questionList;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ReviseActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);

        questionList = new ArrayList<>();

        if(savedInstanceState != null) {
            return;
        }
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

        questionList.clear();
        presenter.getQuestionList();
    }

    @Override
    public void questionsFetched(List<InterviewItem> questions) {
        sadIconTextView.setVisibility(View.GONE);
        nothingSolvedTextView.setVisibility(View.GONE);
        reviseRecyclerView.setVisibility(View.VISIBLE);

        questionList.addAll(questions);

        if(reviseAdapter == null) {
            reviseAdapter = new ReviseAdapter(ReviseActivity.this, questionList, presenter);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ReviseActivity.this);
            reviseRecyclerView.setLayoutManager(mLayoutManager);
            reviseRecyclerView.setAdapter(reviseAdapter);
            reviseAdapter.notifyDataSetChanged();
        }
        else {
            reviseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void questionListEmpty() {
        sadIconTextView.setVisibility(View.VISIBLE);
        nothingSolvedTextView.setVisibility(View.VISIBLE);
        reviseRecyclerView.setVisibility(View.GONE);
    }

    @OnClick(R.id.nothingSolvedTextView)
    public void nothingSolvedTextViewClick() {
        startActivity(QuestionActivity.getStartIntent(this));
        ReviseActivity.this.finish();
    }

    @Override
    public void reviseQuestion(InterviewItem interviewItem) {
        Intent reviseQuestion = QuestionActivity.getStartIntent(this);
        reviseQuestion.putExtra(Constants.REVISION, true);
        reviseQuestion.putExtra(Constants.REVISING_QUESTION, interviewItem.getQuestion());
        reviseQuestion.putExtra(Constants.REVISING_ANSWER, interviewItem.getAnswer());
        reviseQuestion.putExtra(Constants.REVISING_USER_ANSWER, interviewItem.getUserAnswer());
        startActivity(reviseQuestion);
    }
}