package com.example.notification_service.service;

import com.example.notification_service.entity.NotificationHistory;
import com.example.notification_service.repository.NotificationHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationHistoryService {

    private final NotificationHistoryRepository repository;

    public NotificationHistoryService(NotificationHistoryRepository repository) {
        this.repository = repository;
    }

    public NotificationHistory save(NotificationHistory history) {
        return repository.save(history);
    }

    public List<NotificationHistory> findAll() {
        return repository.findAll();
    }

    public NotificationHistory findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
