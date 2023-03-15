package com.example.gateWayApplication;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    RouteLocator getRoutes(RouteLocatorBuilder builder){
        return builder.routes().route(p->p.path("/auth-app/**").uri("http://localhost:9999/*"))
                .route(p->p.path("/user-app/**").uri("http://localhost:8888/*"))
                .route(p->p.path("/restaurant-app/**").uri("http://localhost:8888/*"))
                .route(p->p.path("/feedback/**").uri("http://localhost:8888/*"))
                .route(p->p.path("/request-app/**").uri("http://localhost:8888/*"))
                .build();
    }
}
