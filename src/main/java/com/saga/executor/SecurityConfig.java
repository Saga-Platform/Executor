package com.saga.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class SecurityConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange(authz -> authz.anyExchange().permitAll())
                .csrf().disable()
                .build();
    }

    @Bean
    public PayloadSocketAcceptorInterceptor authorization(RSocketSecurity security) {
        return security.authorizePayload(authorize -> authorize.anyExchange().permitAll())
                .build();
    }
}