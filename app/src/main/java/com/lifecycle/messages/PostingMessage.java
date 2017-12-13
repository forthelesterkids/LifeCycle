package com.lifecycle.messages;

public class PostingMessage<T> extends EventBusMessage<T> {

    public PostingMessage(T t){
        super(t);
    }

    @Override
    public String toString(){
        return t.getClass().toString();
    }
}
