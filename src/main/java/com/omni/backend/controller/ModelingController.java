package com.omni.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ModelingController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ModelingController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/send/message")
    public void onReceiveMessage(final String message) {
        this.simpMessagingTemplate.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date() + " - " + message));
    }
}
