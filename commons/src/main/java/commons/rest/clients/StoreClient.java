package commons.rest.clients;

import commons.rest.config.CustomFeignClientConfiguration;
import commons.rest.models.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "products-management",
//        url = "${app.services.products-management.url}",
        configuration = CustomFeignClientConfiguration.class
)
public interface StoreClient {

    @GetMapping("/stores/{id}")
    Store findStoreById(@PathVariable("id") Long id);

}
