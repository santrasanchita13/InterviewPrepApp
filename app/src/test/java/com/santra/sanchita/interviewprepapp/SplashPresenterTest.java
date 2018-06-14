package com.santra.sanchita.interviewprepapp;

import com.santra.sanchita.interviewprepapp.data.DataManager;
import com.santra.sanchita.interviewprepapp.data.network.model.InterviewNetworkModel;
import com.santra.sanchita.interviewprepapp.data.network.model.Question;
import com.santra.sanchita.interviewprepapp.utils.rx.TestSchedulerProvider;
import com.santra.sanchita.interviewprepapp.ui.splash.SplashMvpView;
import com.santra.sanchita.interviewprepapp.ui.splash.SplashPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;

/**
 * Created by sanchita on 14/6/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class SplashPresenterTest {

    @Mock
    SplashMvpView view;

    @Mock
    DataManager dataManager;

    private TestScheduler testScheduler;

    private SplashPresenter<SplashMvpView> presenter;

    private CompositeDisposable compositeDisposable;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUpSplashPresenter() {

        compositeDisposable = new CompositeDisposable();
        testScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(testScheduler);
        presenter = new SplashPresenter<>(dataManager, testSchedulerProvider, compositeDisposable);
        presenter.onAttach(view);
    }

    @Test
    public void loadApp_InitiateDb() {

        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setQuestion("Hi");
        question1.setAnswer("Hello");
        Question question2 = new Question();
        question2.setQuestion("Hi2");
        question2.setAnswer("Hello2");
        questions.add(question1);
        questions.add(question2);

        InterviewNetworkModel interviewNetworkModel = new InterviewNetworkModel();

        interviewNetworkModel.setQuestions(questions);

        Mockito.doReturn(Observable.just(interviewNetworkModel))
                .when(dataManager).getQuestions();

        presenter.syncDatabase();

        testScheduler.triggerActions();

        verify(view).nextActivity();
    }

    @After
    public void tearDown() throws Exception {
        presenter.onDetach();
    }
}
