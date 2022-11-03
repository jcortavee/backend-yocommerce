package commons.rest.clients;

import commons.rest.config.CustomFeignClientConfiguration;
import commons.rest.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-management",
//        url = "${app.services.users-management.url}",
    configuration = CustomFeignClientConfiguration.class)
public interface UserClient {

    @GetMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);

    @GetMapping("/users/{email}")
    User findByEmail(@PathVariable("email") String email);

}
