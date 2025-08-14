package com.bms.booking_service.feign;

import com.bms.booking_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "SHOW-SERVICE", configuration = FeignConfig.class)
public interface ShowClient {

    @PostMapping("shows/isShowAvailable/{showId}")
    public ResponseEntity<Map<String, List<String>>> validateBooking(@PathVariable String showId, @RequestBody List<String> seats);

    }
