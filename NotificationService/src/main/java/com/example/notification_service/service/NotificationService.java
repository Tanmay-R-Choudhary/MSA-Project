package com.example.notification_service.service;

import com.example.notification_service.entity.Notification;
import com.example.notification_service.entity.NotificationStatus;
import com.example.notification_service.repository.NotificationRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNotification(Long userId, String message) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setStatus(NotificationStatus.UNREAD);
        notificationRepository.save(notification);

        // Send notification to RabbitMQ
        rabbitTemplate.convertAndSend("notificationExchange", "notificationRoutingKey", notification);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    public void markNotificationAsRead(Long notificationId) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setStatus(NotificationStatus.READ);
            notificationRepository.save(notification);
        } else {
            throw new RuntimeException("Notification not found with id: " + notificationId);

        }
    }
}