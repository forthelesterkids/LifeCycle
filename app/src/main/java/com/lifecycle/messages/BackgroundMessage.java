package com.lifecycle.messages;

public class BackgroundMessage<T> extends EventBusMessage<T> {

    public BackgroundMessage(T t){
        super(t);
    }

    @Override
    public String toString(){
        return t.getClass().toString();
    }
}
