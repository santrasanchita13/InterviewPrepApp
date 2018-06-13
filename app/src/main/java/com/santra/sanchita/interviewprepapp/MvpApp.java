package com.santra.sanchita.interviewprepapp;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.santra.sanchita.interviewprepapp.data.DataManager;
import com.santra.sanchita.interviewprepapp.di.component.ApplicationComponent;
import com.santra.sanchita.interviewprepapp.di.component.DaggerApplicationComponent;
import com.santra.sanchita.interviewprepapp.di.module.ApplicationModule;
import com.santra.sanchita.interviewprepapp.utils.AppLogger;

import javax.inject.Inject;

/**
 * Created by sanchita on 14/4/18.
 */

public class MvpApp extends MultiDexApplication {

    @Inject
    DataManager dataManager;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);

        AppLogger.init();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent component) {
        applicationComponent = component;
    }
}

