package com.lifecycle.messages;

public class EmptyMessage<T> extends EventBusMessage<T> {

    public EmptyMessage(T t){
        super(t);
    }

    @Override
    public String toString(){
        return t.getClass().toString();
    }
}
