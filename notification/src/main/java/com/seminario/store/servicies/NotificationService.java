package com.seminario.store.servicies;


import com.seminario.store.models.Notification;
import commons.rest.models.NotificationRequest;

import java.util.List;

public interface NotificationService {
    List<Notification> findAll();
    Notification findById(Long id);
    Notification sendNotification(NotificationRequest notification);
}
