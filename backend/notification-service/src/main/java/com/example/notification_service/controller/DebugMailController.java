package com.example.notification_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugMailController {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @GetMapping("/debug-mail")
    public String debug() {
        return "SMTP utilisé : " + host + " | username : " + username;
    }
}
