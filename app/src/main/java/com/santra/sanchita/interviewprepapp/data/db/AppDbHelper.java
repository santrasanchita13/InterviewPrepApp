package com.santra.sanchita.interviewprepapp.data.db;

import com.santra.sanchita.interviewprepapp.data.db.model.DaoMaster;
import com.santra.sanchita.interviewprepapp.data.db.model.DaoSession;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItemDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by sanchita on 21/11/17.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession daoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        daoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<Long> addToOfflineDb(InterviewItem interviewItem) {
        return Observable.fromCallable(() -> daoSession.getInterviewItemDao().insert(interviewItem));
    }

    @Override
    public Observable<List<InterviewItem>> getInterviewQuestions() {
        return Observable.fromCallable(() -> daoSession.getInterviewItemDao().loadAll());
    }

    @Override
    public Observable<List<InterviewItem>> getUnsolvedQuestions() {
        return Observable.fromCallable(() -> daoSession.getInterviewItemDao()
                .queryBuilder().where(InterviewItemDao.Properties.Solved.eq(false)).list());
    }

    @Override
    public Observable<Boolean> updateAnswer(InterviewItem interviewItem) {
        return Observable.fromCallable(() -> {
            daoSession.getInterviewItemDao().update(interviewItem);
            return true;
        });
    }
}
