package com.example.notification_service.controller;

import com.example.notification_service.entity.Notification;
import com.example.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public String sendNotification(
            @RequestParam Long userId,
            @RequestParam String message) {
        notificationService.sendNotification(userId, message);
        return "In-app notification sent successfully!";
    }

    @GetMapping("/unread")
    public List<Notification> getUnreadNotifications(@RequestParam Long userId) {
        return notificationService.getUnreadNotifications(userId);
    }

    @PutMapping("/mark-as-read/{notificationId}")
    public String markNotificationAsRead(@PathVariable Long notificationId) {
        notificationService.markNotificationAsRead(notificationId);
        return "Notification marked as read!";
    }
}