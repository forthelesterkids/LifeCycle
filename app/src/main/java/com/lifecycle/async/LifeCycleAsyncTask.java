package com.lifecycle.async;

import android.os.AsyncTask;

import com.lifecycle.application.LifecycleApplication;
import com.lifecycle.interfaces.LifecycleCallback;
import com.lifecycle.messages.AsyncMessage;
import com.lifecycle.service.EventBusInterface;

import java.lang.ref.WeakReference;


public class LifeCycleAsyncTask extends AsyncTask<String, Void, String> {

    private static final String TAG = "LifeCycleAsyncTask";
    private WeakReference<LifecycleCallback> weakCallback;
    private EventBusInterface eventBusInterface;

    public LifeCycleAsyncTask(LifecycleCallback weakCallback, EventBusInterface eventBusInterface){
        this.weakCallback = new WeakReference<LifecycleCallback>(weakCallback);
        this.eventBusInterface = eventBusInterface;
    }

    @Override
    protected String doInBackground(String... strings){
        LifecycleApplication.addToMap(TAG, "protected String doInBackground(String... strings)");
        eventBusInterface.postToAsync(new AsyncMessage("Message from AsyncTask"));
        return "WeakCallback";
    }

    @Override
    protected void onPostExecute(String string){
        LifecycleApplication.addToMap(TAG, string);
        weakCallback.get().updateCallback(string);
    }
}
