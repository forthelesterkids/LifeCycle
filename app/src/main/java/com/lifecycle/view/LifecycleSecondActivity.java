package com.lifecycle.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lifecycle.application.LifecycleApplication;
import com.lifecycle.R;

public class LifecycleSecondActivity extends AppCompatActivity {

    private static final String TAG = "LifecycleSecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LifecycleApplication.addToMap(TAG, "protected void onCreate(Bundle savedInstanceState)");
    }

    @Override
    public void onResume(){
        super.onResume();
        LifecycleApplication.addToMap(TAG, "public void onResume()");
    }

    @Override
    public void onStart(){
        super.onStart();
        LifecycleApplication.addToMap(TAG, "public void onStart()");
    }

    @Override
    public void onPause(){
        super.onPause();
        LifecycleApplication.addToMap(TAG, "public void onPause()");
    }
}
