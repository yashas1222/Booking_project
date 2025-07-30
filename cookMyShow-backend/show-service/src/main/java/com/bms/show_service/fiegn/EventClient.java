package com.bms.show_service.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EVENT-SERVICE" , configuration = FeignClientConfig.class)
public interface EventClient {
    @GetMapping("event/getTitle/{eventId}")
    public String getEventTitle(@PathVariable String eventId);

}
