package com.santra.sanchita.interviewprepapp.di.component;

import com.santra.sanchita.interviewprepapp.di.PerActivity;
import com.santra.sanchita.interviewprepapp.di.module.ActivityModule;
import com.santra.sanchita.interviewprepapp.ui.login.LoginActivity;
import com.santra.sanchita.interviewprepapp.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by sanchita on 6/12/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(LoginActivity activity);
}
