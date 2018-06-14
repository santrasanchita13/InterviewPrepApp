package com.santra.sanchita.interviewprepapp;

import com.santra.sanchita.interviewprepapp.data.DataManager;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.utils.rx.TestSchedulerProvider;
import com.santra.sanchita.interviewprepapp.ui.question.QuestionMvpView;
import com.santra.sanchita.interviewprepapp.ui.question.QuestionPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by sanchita on 14/6/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class QuestionPresenterTest {

    public QuestionPresenterTest() {
        super();
    }

    @Mock
    QuestionMvpView view;

    @Mock
    DataManager dataManager;

    private TestScheduler testScheduler;

    private QuestionPresenter<QuestionMvpView> presenter;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUpSplashPresenter() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        testScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(testScheduler);
        presenter = new QuestionPresenter<>(dataManager, testSchedulerProvider, compositeDisposable);
    }

    @Test
    public void checkGetQuestionAndUpdate() {

        List<InterviewItem> questionList = new ArrayList<>();
        questionList.add(new InterviewItem("Hi", "Hello", "", false));
        questionList.add(new InterviewItem("Hi2", "Hello2", "", false));

        doReturn(Observable.just(questionList))
                .when(dataManager)
                .getUnsolvedQuestions();

        presenter.onAttach(view);

        presenter.getQuestion();

        testScheduler.triggerActions();

        verify(view).questionFetched(questionList.get(0));

        InterviewItem interviewItem = new InterviewItem("Hi", "Hi2", "", false);

        doReturn(Observable.just(true))
                .when(dataManager)
                .updateAnswer(interviewItem);

        presenter.updateAnswer(interviewItem);

        testScheduler.triggerActions();

        verify(view).submittedAnswer();
    }

    @After
    public void tearDown() throws Exception {
        presenter.onDetach();
    }
}
