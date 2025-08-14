package com.bms.api_gateway.config;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@Order(1)
public class JwtValidationFilter implements GlobalFilter {

    private final WebClient webClient;

    public JwtValidationFilter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://AUTH-SERVICE") // Eureka service name
                .build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorizedResponse(exchange, "Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        return webClient.post()
                .uri("auth/validate")
                .bodyValue(Map.of("token", token))
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new RuntimeException("Token validation failed")))
                .toBodilessEntity()
                .flatMap(response -> chain.filter(exchange))
                .onErrorResume(e -> unauthorizedResponse(exchange, e.getMessage()));
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        var buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}
