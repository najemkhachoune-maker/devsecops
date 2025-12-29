package com.skillverse.authservice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventPublisher {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public void publishUserEvent(EventType eventType, UUID userId, String email, Map<String, Object> payload) {
        UserEvent event = UserEvent.builder()
                .eventId(UUID.randomUUID())
                .eventType(eventType)
                .userId(userId)
                .email(email)
                .timestamp(LocalDateTime.now())
                .payload(payload)
                .serviceSource("auth-service")
                .build();

        String topic = getTopicForEventType(eventType);
        kafkaTemplate.send(topic, userId.toString(), event);
        log.info("Published event {} to topic {} for user {}", eventType, topic, userId);
    }

    private String getTopicForEventType(EventType eventType) {
        return switch (eventType) {
            case USER_CREATED -> "user.created";
            case USER_UPDATED -> "user.updated";
            case USER_DELETED -> "user.deleted";
            case USER_LOGIN -> "user.login";
            case USER_LOGOUT -> "user.logout";
            case USER_ROLE_CHANGED -> "user.role.changed";
        };
    }
}
