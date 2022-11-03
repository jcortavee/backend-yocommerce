package com.seminario.store.security;

import commons.rest.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final WebClient.Builder webClient;

    public UserService(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("HERE WE ARE");

        try {
            User user = webClient.build().get()
                    .uri("http://user-management/users/by-email/%s".formatted(email))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();

            log.info("User logged: " + user.getEmail());
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                    true, true, true, true,
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        } catch (RuntimeException e) {
            String error = "Error en el login, no existe el usuario " + email +
                    " en el sistema";
            log.error(error);
            log.error(e.getMessage());
            throw new UsernameNotFoundException(error);
        }

    }
}
