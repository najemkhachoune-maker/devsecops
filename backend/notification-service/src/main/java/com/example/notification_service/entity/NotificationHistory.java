package com.example.notification_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class NotificationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String title;
    private String body;
    private LocalDateTime dateSent;
    private String status; // SENT, FAILED

    // Constructeurs
    public NotificationHistory() {}
    public NotificationHistory(String token, String title, String body, LocalDateTime dateSent, String status) {
        this.token = token;
        this.title = title;
        this.body = body;
        this.dateSent = dateSent;
        this.status = status;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public LocalDateTime getDateSent() { return dateSent; }
    public void setDateSent(LocalDateTime dateSent) { this.dateSent = dateSent; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
