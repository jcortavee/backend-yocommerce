package com.seminario.store.rabbitmq;

import com.seminario.store.servicies.NotificationService;
import commons.rest.models.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(
            queues = "${rabbitmq.queues.notification}"
    )
    public void consumer(NotificationRequest request) {
        log.info("Consumed {} from queue", request);
        notificationService.sendNotification(request);
    }

}
