package com.santra.sanchita.interviewprepapp.ui.revise;

import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by sanchita on 13/6/18.
 */

public interface ReviseMvpView extends MvpView {

    void questionsFetched(List<InterviewItem> question);

    void questionListEmpty();

    void reviseQuestion(InterviewItem interviewItem);
}
