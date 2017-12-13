package com.lifecycle.service;

import android.app.IntentService;
import android.content.Intent;

import com.lifecycle.application.LifecycleApplication;

public class LifecycleIntentService extends IntentService {

    private static final String TAG = "LifecycleIntentService";
    public LifecycleIntentService() {
        super(TAG);
    }
    @Override
    public void onCreate(){
        super.onCreate();
        LifecycleApplication.addToMap(TAG, "public void onCreate()");
    }
    @Override
    public void onHandleIntent(Intent intent){
        LifecycleApplication.addToMap(TAG, "public void onHandleIntent(Intent intent)");

    }
}


