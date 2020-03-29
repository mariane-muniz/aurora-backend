package com.omni.backend.service.impl;

import com.omni.backend.service.MessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class MessageQueueServiceImpl implements MessageQueueService {

    private Queue queue;
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    public MessageQueueServiceImpl(JmsMessagingTemplate jmsMessagingTemplate, Queue queue) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.queue = queue;
    }

    @Override
    public void send(final String message) throws MessagingException {
        this.jmsMessagingTemplate.convertAndSend(this.queue, message);
    }
}