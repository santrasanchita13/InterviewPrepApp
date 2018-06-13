package com.santra.sanchita.interviewprepapp.ui.question;

import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.ui.base.MvpView;

/**
 * Created by sanchita on 13/6/18.
 */

public interface QuestionMvpView extends MvpView {

    void questionFetched(InterviewItem question);

    void questionListEmpty();

    void submittedAnswer();
}
