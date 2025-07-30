package com.bms.event_service.feign;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("AUTH-SERVICE")
public interface AuthClient {


    @PostMapping("auth/validate")
    public void validateToken(@RequestBody Map<String,String > body);

}
