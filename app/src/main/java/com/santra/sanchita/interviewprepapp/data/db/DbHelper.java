package com.santra.sanchita.interviewprepapp.data.db;

import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by sanchita on 20/11/17.
 */

public interface DbHelper {

    Observable<Long> addToOfflineDb(InterviewItem interviewItem);

    Observable<List<InterviewItem>> getInterviewQuestions();
}
