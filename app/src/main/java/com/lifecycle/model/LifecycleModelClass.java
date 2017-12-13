package com.lifecycle.model;

import com.lifecycle.application.LifecycleApplication;

import java.io.Serializable;

public class LifecycleModelClass<T> implements Serializable{

    private Class<T> clazz;

    public LifecycleModelClass(Class<T> clazz){
        this.clazz = clazz;
        LifecycleApplication.addToMap("LifecycleModelClass", "modelClass:" + this.clazz.getName());
    }

    @Override
    public String toString(){
        return this.clazz.getName();
    }
}
