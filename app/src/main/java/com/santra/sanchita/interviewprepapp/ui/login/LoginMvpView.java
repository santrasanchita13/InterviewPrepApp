package com.santra.sanchita.interviewprepapp.ui.login;

import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.ui.base.MvpView;

/**
 * Created by sanchita on 13/6/18.
 */

public interface LoginMvpView extends MvpView {
    void addToList(InterviewItem interviewItem);
}
