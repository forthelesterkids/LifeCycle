package com.lifecycle.messages;

public class AsyncMessage<T> extends EventBusMessage<T> {

    public AsyncMessage(T t){
        super(t);
    }

    @Override
    public String toString(){
        return t.getClass().toString();
    }
}
