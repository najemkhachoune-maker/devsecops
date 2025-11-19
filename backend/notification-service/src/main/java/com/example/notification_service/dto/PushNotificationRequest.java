package com.example.notification_service.dto;

public class PushNotificationRequest {

    private String token;
    private String title;
    private String body;

    // Constructeur vide
    public PushNotificationRequest() {}

    // Constructeur complet (optionnel)
    public PushNotificationRequest(String token, String title, String body) {
        this.token = token;
        this.title = title;
        this.body = body;
    }

    // Getters et Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
