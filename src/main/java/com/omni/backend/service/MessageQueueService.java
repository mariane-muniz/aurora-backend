package com.omni.backend.service;

public interface MessageQueueService {
    void send(String message);
}
