package com.bms.show_service.feign;

import com.bms.show_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EVENT-SERVICE" , configuration = FeignConfig.class)
public interface EventClient {
    @GetMapping("events/getTitle/{eventId}")
    public String getEventTitle(@PathVariable String eventId);

}
