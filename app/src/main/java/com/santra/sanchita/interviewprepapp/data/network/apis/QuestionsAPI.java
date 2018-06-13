package com.santra.sanchita.interviewprepapp.data.network.apis;

import com.santra.sanchita.interviewprepapp.data.network.model.InterviewNetworkModel;
import com.santra.sanchita.interviewprepapp.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by sanchita on 13/6/18.
 */

public interface QuestionsAPI {
    @GET("/api/android/datastructure")
    @Headers(Constants.REQUEST_HEADER)
    Call<InterviewNetworkModel> getQuestionList();
}
