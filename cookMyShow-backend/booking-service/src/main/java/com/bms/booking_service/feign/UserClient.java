package com.bms.booking_service.feign;


import com.bms.booking_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("user/isUser/{email}")
    public  boolean isUser(@PathVariable String email);

}
