package com.seminario.store.service;

import commons.rest.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class UsersService implements ReactiveUserDetailsService {

    private final WebClient.Builder client;
    private Logger log = LoggerFactory.getLogger(UsersService.class);

    public UsersService(WebClient.Builder client) {
        this.client = client;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        try {
//            User user = client.build().get()
//                    .uri("http://user-management/users/by-email/%s".formatted(email))
//                    .accept(MediaType.APPLICATION_JSON)
//                    .retrieve()
//                    .bodyToMono(User.class)
//                    .block();
//            log.info("User login: " + user.getEmail());
//            log.info("User login: " + user.getPassword());
//
//            return new org.springframework.security.core.userdetails.User(email, user.getPassword(), true, true, true, true,
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
//        } catch (RuntimeException e) {
//            String error = "The user does not exists";
//            throw new UsernameNotFoundException(error);
//        }
//    }

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        try {
            var user = client.build().get()
                    .uri("http://user-management/users/by-email/%s".formatted(email))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(User.class);


            return user.switchIfEmpty(Mono.defer(() -> Mono.error(new UsernameNotFoundException("User Not Found")))).map(u -> {
                log.info("User login: " + u.getEmail());
                log.info("User login: " + u.getPassword());
                return new org.springframework.security.core.userdetails.User(email, u.getPassword(), true, true, true, true,
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
            });
        } catch (RuntimeException e) {
            String error = "The user does not exists";
            throw new UsernameNotFoundException(error);
        }
    }
}
