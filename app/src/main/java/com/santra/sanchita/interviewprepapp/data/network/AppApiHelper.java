package com.santra.sanchita.interviewprepapp.data.network;

import android.content.Context;

import com.santra.sanchita.interviewprepapp.data.network.apis.QuestionsAPI;
import com.santra.sanchita.interviewprepapp.data.network.model.InterviewNetworkModel;
import com.santra.sanchita.interviewprepapp.data.prefs.AppPreferenceHelper;
import com.santra.sanchita.interviewprepapp.di.ApplicationContext;
import com.santra.sanchita.interviewprepapp.utils.AppLogger;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by sanchita on 21/11/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    Context context;

    AppPreferenceHelper mAppPreferenceHelper;

    @Inject
    public AppApiHelper(@ApplicationContext Context mContext, AppPreferenceHelper appPreferenceHelper) {
        context = mContext;
        mAppPreferenceHelper = appPreferenceHelper;
    }

    @Override
    public Observable<InterviewNetworkModel> getQuestions() {
        return Observable.fromCallable(() -> {
            Retrofit retrofit = RestClient.getInstance(context);
            QuestionsAPI newUserAPI = retrofit.create(QuestionsAPI.class);

            Call<InterviewNetworkModel> call = newUserAPI.getQuestionList();

            try {
                Response<InterviewNetworkModel> response = call.execute();
                if (response != null) {
                    if(response.body() != null) {
                        return response.body();
                    }
                }
            } catch (IOException e) {
                AppLogger.i(e, call.toString());
            }
            return null;
        });
    }
}
