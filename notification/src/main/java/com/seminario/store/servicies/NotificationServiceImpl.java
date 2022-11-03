package com.seminario.store.servicies;

import com.seminario.store.models.Notification;
import com.seminario.store.repositories.NotificationRepository;
import commons.exceptions.RecordNotFoundException;
import commons.rest.models.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public NotificationServiceImpl(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification findById(Long id) {
        var optionalNotification = notificationRepository.findById(id);

        if (optionalNotification.isPresent()) {
            return optionalNotification.get();
        }

        throw new RecordNotFoundException();
    }

    @Override
    @Transactional
    public Notification sendNotification(NotificationRequest notificationRequest) {
        var notification = Notification
                .builder()
                .sentAt(LocalDateTime.now())
                .message(notificationRequest.getMessage())
                .sender(notificationRequest.getSender())
                .toUserEmail(notificationRequest.getToUserEmail())
                .notificationType(notificationRequest.getNotificationType()).build();
        try {
            emailService.sendEmail(notification.getSender(), notification.getToUserEmail(),
                    "User created");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return notificationRepository.save(notification);
    }


}
