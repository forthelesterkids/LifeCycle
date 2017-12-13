package com.lifecycle.service;

import android.util.Log;

import com.lifecycle.application.LifecycleApplication;
import com.lifecycle.messages.AsyncMessage;
import com.lifecycle.messages.BackgroundMessage;
import com.lifecycle.messages.EmptyMessage;
import com.lifecycle.messages.MainMessage;
import com.lifecycle.messages.PostingMessage;
import com.lifecycle.model.LifecycleModelClass;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EventBusContext implements EventBusInterface {

    private EventBus eventBus;
    private static final String TAG = "EventBusContext";
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    public EventBusContext(EventBus eventBus){
        this.eventBus = eventBus;
    }
    @Override
    public void postToMain(){
        Callable<LifecycleModelClass> callable = new Callable<LifecycleModelClass>() {
            @Override
            public LifecycleModelClass call() {
                LifecycleApplication.addToMap(TAG, "Future<LifecycleModelClass> future:public LifecycleModelClass call()");
                eventBus.post(new MainMessage<>(new String()));
                return new LifecycleModelClass(EventBusContext.class);
            }
        };
        Future<LifecycleModelClass> future = executor.submit(callable);
        LifecycleModelClass model = null;
        try {
            model = future.get();
        } catch (InterruptedException ie){
            Log.e(TAG, "InteruptedException:" + ie.getMessage());
        } catch (ExecutionException ee){
            Log.e(TAG, "ExecutionException:" + ee.getMessage());
        }
        //save to realmdb
    }

    @Override
    public void postToAsync(AsyncMessage asyncMessage){
        eventBus.post(new AsyncMessage<>(new LifecycleModelClass<>(this.getClass())));
    }
    @Override
    public void postToAsync(){

        Callable<LifecycleModelClass> callable = new Callable<LifecycleModelClass>() {
            @Override
            public LifecycleModelClass call() {
                LifecycleApplication.addToMap(TAG, "Future<LifecycleModelClass> future:public LifecycleModelClass call()");
                eventBus.post(new AsyncMessage<>(""));
                return new LifecycleModelClass(EventBusContext.class);
            }
        };
        Future<LifecycleModelClass> future = executor.submit(callable);
        LifecycleModelClass model = null;
        try {
            model = future.get();
        } catch (InterruptedException ie){
            Log.e(TAG, "InteruptedException:" + ie.getMessage());
        } catch (ExecutionException ee){
            Log.e(TAG, "ExecutionException:" + ee.getMessage());
        }
        if(model != null) eventBus.post(new AsyncMessage<>(model));
        else eventBus.post(new EmptyMessage<>("Empty Message"));
        //save to realmdb

    }

    @Override
    public void postToBackground(){
        Callable<LifecycleModelClass> callable = new Callable<LifecycleModelClass>() {
            @Override
            public LifecycleModelClass call() {
                LifecycleApplication.addToMap(TAG, "Future<LifecycleModelClass> future:public LifecycleModelClass call()");
                eventBus.post(new BackgroundMessage<>(""));
                return new LifecycleModelClass(EventBusContext.class);
            }
        };
        Future<LifecycleModelClass> future = executor.submit(callable);
        LifecycleModelClass model = null;
        try {
            model = future.get();
        } catch (InterruptedException ie){
            Log.e(TAG, "InteruptedException:" + ie.getMessage());
        } catch (ExecutionException ee){
            Log.e(TAG, "ExecutionException:" + ee.getMessage());
        }
        if(model != null) eventBus.post(new BackgroundMessage<>(model));
        else eventBus.post(new EmptyMessage<>("Empty Message"));
        //savetoRealm
    }

    @Override
    public void postToPost(){
        Callable<LifecycleModelClass> callable = new Callable<LifecycleModelClass>() {
            @Override
            public LifecycleModelClass call() {
                LifecycleApplication.addToMap(TAG, "Future<LifecycleModelClass> future:public LifecycleModelClass call()");
                eventBus.post(new PostingMessage<>(""));
                return new LifecycleModelClass(EventBusContext.class);
            }
        };
        Future<LifecycleModelClass> future = executor.submit(callable);
        LifecycleModelClass model = null;
        try {
            model = future.get();
        } catch (InterruptedException ie){
            Log.e(TAG, "InteruptedException:" + ie.getMessage());
        } catch (ExecutionException ee){
            Log.e(TAG, "ExecutionException:" + ee.getMessage());
        }
        if(model != null) eventBus.post(new PostingMessage<>(model));
        else eventBus.post(new EmptyMessage<>("Empty Message"));
        //savetoRealm
    }

    @Override
    public <T> void register (T subscriber){
        eventBus.register(subscriber);
    }

    @Override
    public <T> void unregister(T subscriber){
        eventBus.unregister(subscriber);

    }

}
