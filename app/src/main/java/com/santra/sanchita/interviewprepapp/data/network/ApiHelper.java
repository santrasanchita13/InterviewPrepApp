package com.santra.sanchita.interviewprepapp.data.network;

import com.santra.sanchita.interviewprepapp.data.network.model.InterviewNetworkModel;

import io.reactivex.Observable;

/**
 * Created by sanchita on 21/11/17.
 */

public interface ApiHelper {
    Observable<InterviewNetworkModel> getQuestions();
}
