package com.seminario.store.controllers;

import com.seminario.store.models.Notification;
import com.seminario.store.servicies.NotificationService;
import commons.rest.models.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("New notification... {}", notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }

    @GetMapping
    public ResponseEntity<List<Notification>> findAll() {
        List<Notification> notifications = notificationService.findAll();

        return ResponseEntity.ok(notifications);
    }

}
