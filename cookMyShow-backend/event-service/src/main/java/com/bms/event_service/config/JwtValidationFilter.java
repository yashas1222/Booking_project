// In your show-service

package com.bms.event_service.config;

import com.bms.event_service.feign.AuthClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@Order(1)
public class JwtValidationFilter extends OncePerRequestFilter {

    @Autowired
    AuthClient showInterface;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return;
        }

        final String token = authHeader.substring(7);


        try {
            Map<String, String> validationRequestBody = Map.of("token", token);
            showInterface.validateToken(validationRequestBody);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            logger.error("Token validation failed", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");

        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs") || path.startsWith("/actuator");
    }
}
