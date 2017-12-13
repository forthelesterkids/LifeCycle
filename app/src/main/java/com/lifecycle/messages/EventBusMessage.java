package com.lifecycle.messages;


public class EventBusMessage<T> {

    T t;

    public EventBusMessage(T t){
        this.t = t;
    }

    @Override
    public String toString(){
        return t.getClass().toString();
    }
}
