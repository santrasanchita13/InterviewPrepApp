package com.santra.sanchita.interviewprepapp.ui.question;

import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.ui.base.MvpPresenter;

import java.util.List;

/**
 * Created by sanchita on 13/6/18.
 */

public interface QuestionMvpPresenter<V extends QuestionMvpView> extends MvpPresenter<V> {

    List<InterviewItem> getQuestion();

    Boolean updateAnswer(InterviewItem interviewItem);
}
