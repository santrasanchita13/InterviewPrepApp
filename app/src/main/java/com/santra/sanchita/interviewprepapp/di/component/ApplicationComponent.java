package com.santra.sanchita.interviewprepapp.di.component;

import android.app.Application;
import android.content.Context;

import com.santra.sanchita.interviewprepapp.MvpApp;
import com.santra.sanchita.interviewprepapp.data.DataManager;
import com.santra.sanchita.interviewprepapp.di.ApplicationContext;
import com.santra.sanchita.interviewprepapp.di.module.ApplicationModule;
import com.santra.sanchita.interviewprepapp.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sanchita on 6/12/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp mvpApp);

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();
}

