package com.bms.auth_service.config;

import com.bms.auth_service.feign.CustomFeignErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public feign.codec.ErrorDecoder errorDecoder() {
        return new CustomFeignErrorDecoder();
    }
}