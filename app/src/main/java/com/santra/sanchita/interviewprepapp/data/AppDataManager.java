package com.santra.sanchita.interviewprepapp.data;

import android.content.Context;

import com.santra.sanchita.interviewprepapp.data.db.DbHelper;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.data.network.ApiHelper;
import com.santra.sanchita.interviewprepapp.data.network.model.InterviewNetworkModel;
import com.santra.sanchita.interviewprepapp.data.prefs.PreferenceHelper;
import com.santra.sanchita.interviewprepapp.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by sanchita on 21/11/17.
 */

@Singleton
public class AppDataManager implements DataManager {

    private final Context context;
    private final DbHelper dbHelper;
    private final PreferenceHelper preferenceHelper;
    private final ApiHelper apiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context, DbHelper dbHelper, PreferenceHelper preferenceHelper, ApiHelper apiHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
        this.preferenceHelper = preferenceHelper;
        this.apiHelper = apiHelper;
    }

    @Override
    public String getAuthCode() {
        return preferenceHelper.getAuthCode();
    }

    @Override
    public void setAuthCode(String authCode) {
        preferenceHelper.setAuthCode(authCode);
    }

    @Override
    public Observable<InterviewNetworkModel> getQuestions() {
        return apiHelper.getQuestions();
    }

    @Override
    public Observable<Long> addToOfflineDb(InterviewItem interviewItem) {
        return dbHelper.addToOfflineDb(interviewItem);
    }

    @Override
    public Observable<List<InterviewItem>> getInterviewQuestions() {
        return dbHelper.getInterviewQuestions();
    }

    @Override
    public Observable<List<InterviewItem>> getUnsolvedQuestions() {
        return dbHelper.getUnsolvedQuestions();
    }

    @Override
    public Observable<Boolean> updateAnswer(InterviewItem interviewItem) {
        return dbHelper.updateAnswer(interviewItem);
    }

    @Override
    public Observable<List<InterviewItem>> getSolvedQuestions() {
        return dbHelper.getSolvedQuestions();
    }
}
