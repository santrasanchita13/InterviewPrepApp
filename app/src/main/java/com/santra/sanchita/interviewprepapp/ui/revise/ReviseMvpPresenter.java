package com.santra.sanchita.interviewprepapp.ui.revise;

import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.ui.base.MvpPresenter;

import java.util.List;

/**
 * Created by sanchita on 13/6/18.
 */

public interface ReviseMvpPresenter<V extends ReviseMvpView> extends MvpPresenter<V> {

    void getQuestionList();

    Boolean updateAnswer(InterviewItem interviewItem);

}
