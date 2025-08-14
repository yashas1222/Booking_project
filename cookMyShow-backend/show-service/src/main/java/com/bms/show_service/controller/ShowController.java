package com.bms.show_service.controller;


import com.bms.show_service.dto.ScreenwiseShowResponseDto;
import com.bms.show_service.dto.ShowAvailabilityResponseDto;
import com.bms.show_service.dto.ShowRequestDto;
import com.bms.show_service.model.Show;
import com.bms.show_service.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("shows")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/create")
    public ResponseEntity<Show> createShow(@Valid @RequestBody ShowRequestDto showRequestDto){
            return showService.createShow(showRequestDto);
    }

    @PostMapping("/create/bulk")
    public ResponseEntity<List<Show>> createBulkShows(@Valid @RequestBody List<ShowRequestDto> showRequestDtos) {
        return ResponseEntity.ok(showService.createBulkShows(showRequestDtos));
    }


    @GetMapping("/{showId}")
    public ResponseEntity<Show> getShow(@PathVariable String showId){
        return showService.getShow(showId);
    }

    @GetMapping("")
    public  ResponseEntity<List<Show>> getAllShows(){
        return showService.getAllShows();
    }

    @GetMapping("/theater/{theatreId}")
    public ResponseEntity<List<Show>> getShowsForTheater(@PathVariable String theatreId){
        return showService.getShowsForTheatre(theatreId);
    }

    @GetMapping("/available")
    public  ResponseEntity<ShowAvailabilityResponseDto> getTheatersWithShowsAvailable(
            @RequestParam String eventId,
            @RequestParam String city,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            ){
        return showService.getTheatersWithShowsAvailable(eventId, city, date);
    }

    @GetMapping("/screenwise")
    public ResponseEntity<ScreenwiseShowResponseDto> getShowsForTheatreOnDate(
            @RequestParam String eventId,
            @RequestParam String theatreId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return showService.getShowsForTheatreOnDate(eventId, theatreId, date);
    }

    @PostMapping("/isShowAvailable/{showId}")
    public ResponseEntity<Map<String, List<String>>> validateBooking(@PathVariable String showId,@RequestBody List<String> seats){
return showService.validateBooking(showId,seats);
    }





}
