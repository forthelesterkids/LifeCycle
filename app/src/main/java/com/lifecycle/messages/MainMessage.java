package com.lifecycle.messages;

public class MainMessage<T> extends EventBusMessage<T> {

    public MainMessage(T t){
        super(t);
    }

    @Override
    public String toString(){
        return t.getClass().toString();
    }
}
