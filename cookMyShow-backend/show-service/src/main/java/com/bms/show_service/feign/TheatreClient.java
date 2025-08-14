package com.bms.show_service.feign;

import com.bms.show_service.dto.TheatreInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("THEATRE-SERVICE")
public interface TheatreClient {


    @PostMapping("theatres/city/{city}")
    public ResponseEntity<List<TheatreInfoDto>> getTheatresByIdAndCity(
            @PathVariable String city,
            @RequestBody List<String> theatreIds
    );


    @GetMapping("theatres/{theatreId}")
    public ResponseEntity<TheatreInfoDto> getTheatre(@PathVariable String theatreId);

}
