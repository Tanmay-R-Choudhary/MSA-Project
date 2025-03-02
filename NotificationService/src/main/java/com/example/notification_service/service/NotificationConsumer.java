package com.example.notification_service.service;

import com.example.notification_service.entity.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = "notificationQueue")
    public void receiveNotification(Notification notification) {
        // Broadcast the notification to WebSocket clients
        messagingTemplate.convertAndSend("/topic/notifications/" + notification.getUserId(), notification);
    }
}