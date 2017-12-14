package com.lifecycle.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lifecycle.R;
import com.lifecycle.application.LifecycleApplication;
import com.lifecycle.async.LifeCycleAsyncTask;
import com.lifecycle.interfaces.LifecycleCallback;
import com.lifecycle.messages.AsyncMessage;
import com.lifecycle.messages.BackgroundMessage;
import com.lifecycle.messages.EmptyMessage;
import com.lifecycle.messages.MainMessage;
import com.lifecycle.messages.PostingMessage;
import com.lifecycle.service.EventBusContext;
import com.lifecycle.service.LifecycleBackgroundService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LifecycleMainActivity extends AppCompatActivity implements LifecycleCallback{

    private Button button;
    private LifecycleBackgroundService mService = new LifecycleBackgroundService();
    private boolean mBound = false;
    private static final String TAG = "LifecycleMainActivity";
    private EventBus eventBus = EventBus.getDefault();
    private EventBusContext eventBusContext;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            LifecycleBackgroundService.LifecycleBinder binder = (LifecycleBackgroundService.LifecycleBinder) service;
            mService = binder.getService();
            mBound = true;
            if(mService != null){
                Log.i("service-bind", "Service is bonded successfully!");

                //do whatever you want to do after successful binding
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            LifecycleApplication.addToMap(TAG, "public void handleMessage(Message msg)" + msg.toString());
            LifecycleApplication.spillLogs();
        }
    };

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onMainMessage(MainMessage message) {
        long id = Thread.currentThread().getId();
        LifecycleApplication.addToMap(TAG, "public void onMainMessage(MainMessage message):" + message.toString());
        button.setText("testing2");
        LifecycleApplication.spillLogs();
    }

    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundMessage(BackgroundMessage message) {
        LifecycleApplication.addToMap(TAG, "public void onBackgroundMessage(BackgroundMessage message)" + message.toString());
        try {
            long id = Thread.currentThread().getId();
            button.setText("testing3");
        } catch (Exception e){
            LifecycleApplication.addToMap(TAG, "updating the ui should fail here!" + e.getMessage());
        } finally {
            LifecycleApplication.spillLogs();
        }
    }

    @Subscribe (threadMode = ThreadMode.POSTING)
    public void onPostingMessage(PostingMessage message) {
        LifecycleApplication.addToMap(TAG, "public void onBackgroundMessage(BackgroundMessage message)" + message.toString());
        try {
            long id = Thread.currentThread().getId();
            button.setText("testing3");
        } catch (Exception e){
            LifecycleApplication.addToMap(TAG, "updating the ui should fail here!" + e.getMessage());
        } finally {
            LifecycleApplication.spillLogs();
        }
    }

    @Subscribe (threadMode = ThreadMode.ASYNC)
    public void onAsyncMessage(AsyncMessage message) {
        long id = Thread.currentThread().getId();
        LifecycleApplication.addToMap(TAG, "public void onAsyncMessage(AsyncMessage message)" + message.toString());
        try {
            button.setText("testing1");
        } catch (Exception e){
            LifecycleApplication.addToMap(TAG, "updating the ui should fail here!" + e.getMessage());
        } finally {
            LifecycleApplication.spillLogs();
        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onEmptyMessage(EmptyMessage message) {
        long id = Thread.currentThread().getId();
        LifecycleApplication.addToMap(TAG, "public void onEmptyMessage(EmptyMessage message)" + message.toString());
        LifecycleApplication.spillLogs();
    }

    @Override
    public void updateCallback(String callback){
        LifecycleApplication.addToMap(TAG, "public void updateCallback(String callback)" + callback.toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LifecycleApplication.addToMap(TAG, "protected void onCreate(Bundle savedInstanceState)");
        button = (Button)findViewById(R.id.button);
        addListeners();
        eventBusContext = new EventBusContext(eventBus);
        new LifeCycleAsyncTask(this, eventBusContext).execute("LifeCycleAsyncTask async running");
        publishMessages();
    }

    private void publishMessages(){
        eventBusContext.postToAsync();
        eventBusContext.postToMain();
        eventBusContext.postToBackground();
        eventBusContext.postToPost();
    }
    private void addListeners(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LifecycleApplication.addToMap(TAG, "button.setOnTouchListener(new View.OnTouchListener()");
                LifecycleApplication.spillLogs();
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        Intent intent = new Intent(this, LifecycleBackgroundService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        mService.searchInternet(handler);
        eventBusContext.register(this);
        LifecycleApplication.addToMap(TAG, "public void onResume()");
    }

    @Override
    public void onStart(){
        super.onStart();
        LifecycleApplication.addToMap(TAG, "public void onStart()");
    }

    @Override
    public void onStop(){
        super.onStop();
        unbindService(mConnection);
        mBound = false;
    }
    @Override
    public void onPause(){
        super.onPause();
        eventBusContext.unregister(this);
        LifecycleApplication.addToMap(TAG, "public void onPause()");
    }

}
