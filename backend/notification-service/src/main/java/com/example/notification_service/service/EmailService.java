package com.example.notification_service.service;

import com.example.notification_service.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Email par défaut défini dans application.properties
    @Value("${spring.mail.from}")
    private String defaultFrom;

    public void sendEmail(EmailRequest emailRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            // Choix dynamique du champ "from" : si non fourni, utilise la valeur par défaut
            message.setFrom(emailRequest.getFrom() != null && !emailRequest.getFrom().isEmpty() 
                            ? emailRequest.getFrom() 
                            : defaultFrom);

            message.setTo(emailRequest.getTo());
            message.setSubject(emailRequest.getSubject());
            message.setText(emailRequest.getBody());

            mailSender.send(message);
            System.out.println("Email envoyé avec succès à: " + emailRequest.getTo() 
                               + " depuis: " + message.getFrom());
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
            throw new RuntimeException("Impossible d'envoyer l'email", e);
        }
    }
}
