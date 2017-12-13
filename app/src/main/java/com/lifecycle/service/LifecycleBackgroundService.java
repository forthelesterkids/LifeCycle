package com.lifecycle.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LifecycleBackgroundService extends Service {
    private static final String TAG = "LifecycleBackground";
    private final IBinder mBinder = new LifecycleBinder();

    public class LifecycleBinder extends Binder {
        public LifecycleBackgroundService getService() {
            return LifecycleBackgroundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

}


