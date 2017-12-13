package com.lifecycle.service;

import com.lifecycle.messages.AsyncMessage;

public interface EventBusInterface {
    void postToMain();
    void postToAsync();
    void postToBackground();
    void postToPost();
    <T> void register (T subscriber);
    <T> void unregister(T unsubscriber);
    void postToAsync(AsyncMessage asyncMessage);
}
