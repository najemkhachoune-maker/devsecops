package com.example.notification_service.service;

import com.example.notification_service.entity.NotificationHistory;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationPush {

    private final NotificationHistoryService historyService;

    // Injection de NotificationHistoryService
    public NotificationPush(NotificationHistoryService historyService) {
        this.historyService = historyService;
    }

    public void sendNotification(String token, String title, String body) {
        String status = "SENT"; // Statut par défaut

        try {
            // Crée la notification FCM
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build();

            // Crée le message pour un device spécifique via son token
            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(notification)
                    .build();

            // Envoie la notification via FCM
            String response = FirebaseMessaging.getInstance().send(message);

            // Affiche la réponse pour le debug
            System.out.println("✅ Réponse de FCM : " + response);
            System.out.println("✅ Notification envoyée avec succès au token : " + token);

        } catch (Exception e) {
            status = "FAILED"; // Met à jour le statut en cas d'erreur
            System.err.println("❌ Erreur lors de l’envoi de la notification : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Enregistrer dans l'historique
            NotificationHistory history = new NotificationHistory();
            history.setToken(token);
            history.setTitle(title);
            history.setBody(body);
            history.setDateSent(LocalDateTime.now());
            history.setStatus(status);

            // Sauvegarde dans la base de données
            historyService.save(history);
            System.out.println("📌 Notification enregistrée dans l'historique avec statut : " + status);
        }
    }
}
