package com.lifecycle.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.lifecycle.application.LifecycleApplication;
import com.lifecycle.model.LifecycleModelClass;

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
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }


    public void searchInternet(Handler handler) {
        LifeCycleRunnable runnable = new LifeCycleRunnable(handler);
        Thread t = new Thread(runnable);
        t.start();
    }

    class LifeCycleRunnable implements Runnable {

        Handler handler;
        private final String TAG = "LifeCycleRunnable";
        public LifeCycleRunnable(Handler handler){
            this.handler = handler;
        }
        @Override
        public void run(){
            LifecycleApplication.addToMap(TAG, "public void run()");
            Message completeMessage = new Message();
            completeMessage.obj = new LifecycleModelClass<>(Handler.class);
            handler.dispatchMessage(completeMessage);
        }
    }

}


