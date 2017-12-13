package com.lifecycle.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lifecycle.application.LifecycleApplication;

public class LifecycleReceiver extends BroadcastReceiver {

    private static final String TAG = "LifecycleReceiver";
    public static final String LIFECYCLE_RECEIVER_FILTER1 = "com.lifecycle.receiver.filter1";
    public static final String LIFECYCLE_RECEIVER_FILTER2 = "com.lifecycle.receiver.filter2";

    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals(LIFECYCLE_RECEIVER_FILTER1)){
            LifecycleApplication.addToMap(TAG, "public void onReceive(Context context, Intent intent)" + LIFECYCLE_RECEIVER_FILTER1);
        } else if(intent.getAction().equals(LIFECYCLE_RECEIVER_FILTER2)){
            LifecycleApplication.addToMap(TAG, "public void onReceive(Context context, Intent intent):" + LIFECYCLE_RECEIVER_FILTER2);
        }

    }
}
