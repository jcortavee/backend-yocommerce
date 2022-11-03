package com.seminario.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(
        basePackages = {"com.seminario.store", "commons"}
)
@EnableDiscoveryClient
public class ProductsManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductsManagementApplication.class);
    }
}
