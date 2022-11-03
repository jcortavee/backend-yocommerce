package com.seminario.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.Mono;

import static org.springframework.security.config.Customizer.withDefaults;

//@EnableWebFluxSecurity
public class SecurityConfig {

//    @Bean
    public WebSessionManager webSessionManager() {
        return exchange -> Mono.empty();
    }

//    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/authorized").permitAll()
                        .pathMatchers("/").hasAnyAuthority("SCOPE_read", "SCOPE_write")
                        .anyExchange()
                        .authenticated()
                )
                .oauth2Login()
                .and()
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("/oauth2/authorization/superstore")))
                .oauth2Client(withDefaults())
                .oauth2ResourceServer().jwt();
        return http.build();
    }

}
