package com.lifecycle.application;

import android.app.Application;
import android.util.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class LifecycleApplication extends Application {

    private static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
    private static final String TAG = "LifecycleApplication";
    @Override
    public void onCreate(){
        super.onCreate();
    }

    public static void addToMap(String key, String value){
        concurrentHashMap.put(key, value);
        Log.i(TAG, "public static void addToMap(String key, String value)");
    }

    public static void spillLogs(){
        for (Map.Entry<String, String> entry : concurrentHashMap.entrySet()) {
            Log.i(TAG, "Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
    }
}
