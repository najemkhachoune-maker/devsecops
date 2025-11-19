package com.example.notification_service.controller;

import com.example.notification_service.dto.PushNotificationRequest;
import com.example.notification_service.service.NotificationPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/push")
public class NotificationPushController {

    @Autowired
    private NotificationPush notificationPush;

    @PostMapping
    public String sendPush(@RequestBody PushNotificationRequest request) {
        notificationPush.sendNotification(request.getToken(), request.getTitle(), request.getBody());
        return "Notification envoyée avec succès ✅";
    }
}
