package com.lifecycle.thread;

import com.lifecycle.application.LifecycleApplication;

/**
 * Created by christopher.lester on 12/11/17.
 */

public class LifecycleRunnable implements Runnable {

    public void run(){
        LifecycleApplication.addToMap("LifecycleRunnable", "public void start run()");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie){
            LifecycleApplication.addToMap("LifecycleRunnable", "InterruptedException ie");
        }

        LifecycleApplication.addToMap("LifecycleRunnable", "public void end run()");
    }
}
