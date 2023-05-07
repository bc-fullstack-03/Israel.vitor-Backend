package com.api.back.service.User;

public interface IEventService {
    void send(String topic, String event);
}
