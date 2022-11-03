package commons.rest.clients;

import commons.rest.config.CustomFeignClientConfiguration;
import commons.rest.models.NotificationRequest;
import commons.rest.models.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notification",
        configuration = CustomFeignClientConfiguration.class
)
public interface NotificationClient {

    @PostMapping
    void sendNotification(NotificationRequest notificationRequest);

}
