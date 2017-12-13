package com.lifecycle.thread;

/**
 * Created by christopher.lester on 12/11/17.
 */

public class LifecycleThread extends Thread {

    private LifecycleRunnable lifecycleRunnable;

    public LifecycleThread(LifecycleRunnable lifecycleRunnable){
        this.lifecycleRunnable = lifecycleRunnable;
    }
}
