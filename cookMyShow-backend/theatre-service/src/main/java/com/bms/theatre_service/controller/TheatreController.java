package com.bms.theatre_service.controller;


import com.bms.theatre_service.dto.TheatreInfoDto;
import com.bms.theatre_service.dto.TheatreRequestDto;
import com.bms.theatre_service.model.Theatre;
import com.bms.theatre_service.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPageRegistrarBeanPostProcessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("theatres")
public class TheatreController {
    @Autowired
    TheatreService theatreService;

    @PostMapping("/create")
    public ResponseEntity<Theatre> createTheatre(@RequestBody TheatreRequestDto theatreRequestDto){

return theatreService.createTheatre(theatreRequestDto);

    }

    @PostMapping("/create/bulk")
    public ResponseEntity<List<Theatre>> createBulkTheatre(@RequestBody List<TheatreRequestDto> theatreRequestDtos){
        return theatreService.createBulkTheatre(theatreRequestDtos);
    }

    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getTheatres(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page,size);
        return theatreService.getTheatres(pageable);
    }

    @GetMapping("/{theatreId}")
    public ResponseEntity<TheatreInfoDto> getTheatre(@PathVariable String theatreId){
        return theatreService.getTheatre(theatreId);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<Map<String , Object>> getTheatersByCity(@PathVariable String city,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size
                                                                  ){
        Pageable pageable = PageRequest.of(page, size);

        return theatreService.getTheatresByCity(city, pageable);
    }


@PostMapping("/city/{city}")
public  ResponseEntity<List<TheatreInfoDto>> getTheatresByIdAndCity(
        @PathVariable String city,
        @RequestBody List<String> theatreIds

){
        return theatreService.getTheatresByIdAndCity( city, theatreIds);
}

}
