package com.santra.sanchita.interviewprepapp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.santra.sanchita.interviewprepapp.MvpApp;
import com.santra.sanchita.interviewprepapp.data.DataManager;
import com.santra.sanchita.interviewprepapp.di.component.DaggerServiceComponent;
import com.santra.sanchita.interviewprepapp.di.component.ServiceComponent;
import com.santra.sanchita.interviewprepapp.utils.AppLogger;

import javax.inject.Inject;

/**
 * Created by sanchita on 21/11/17.
 */

public class SyncService extends Service {

    private static final String TAG = "SyncService";

    @Inject
    DataManager dataManager;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SyncService.class);
        context.startService(intent);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, SyncService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ServiceComponent component = DaggerServiceComponent.builder()
                .applicationComponent(((MvpApp) getApplication()).getApplicationComponent())
                .build();

        component.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppLogger.d(TAG, "SyncService started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        AppLogger.d(TAG, "SyncService stopped");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
