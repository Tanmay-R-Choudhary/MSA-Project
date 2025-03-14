package com.example.notification_service.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/notifications")
    @SendTo("/topic/notifications")
    public String handleNotification(String message) {
        return message;
    }
}