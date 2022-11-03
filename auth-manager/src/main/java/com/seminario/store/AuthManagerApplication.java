package com.seminario.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthManagerApplication.class, args);
    }
}
