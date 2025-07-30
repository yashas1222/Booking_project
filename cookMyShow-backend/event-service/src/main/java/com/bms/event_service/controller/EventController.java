package com.bms.event_service.controller;

import com.bms.event_service.dto.EventRequestDto;
import com.bms.event_service.dto.EventResponseDto;
import com.bms.event_service.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {

@Autowired
    EventService eventService;
    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@Valid  @RequestBody EventRequestDto dto){
      return  eventService.createEvent(dto);
    }

    @PostMapping("create/bulk")
    public  ResponseEntity<String> createEvents(@Valid @RequestBody List<EventRequestDto> dtos){
        return eventService.createEvents(dtos);
    }

    @GetMapping("/allEvents")
    public ResponseEntity<Map<String, Object>> getAllEvents(
            @RequestParam(defaultValue = "0") int page ,
            @RequestParam(defaultValue = "10") int size
            ){
       Pageable paging = PageRequest.of(page,size);
       return eventService.getAllEvents(paging);
    }

    @GetMapping(value = "/getEvent/{eventId}")
    public ResponseEntity<EventResponseDto> getEvent(@PathVariable String eventId){
        return eventService.getEvent(eventId);
    }

    @PutMapping("/updateEvent/{eventId}")
    public ResponseEntity<EventResponseDto> updateEvent(@Valid @RequestBody EventRequestDto dto, @PathVariable String eventId){
        return eventService.updateEvent(dto,eventId);
    }

    @DeleteMapping("delete/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable String eventId){
        return eventService.deleteEvent(eventId);
    }

    @GetMapping("/type/{type}")
    public  ResponseEntity<Map<String, Object>> getEventsByType(
            @PathVariable String type,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page,size);
        return eventService.getEventsByType(type, pageable);
    }

    @GetMapping("/genre/{genreId}")
    public  ResponseEntity<Map<String , Object>> getEventsByGenre(
            @PathVariable String genreId,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10")int size
            ){

        Pageable pageable = PageRequest.of(page, size);
        return eventService.getEventsByGenre(genreId, pageable);
    }

    @GetMapping("/search")
    public  ResponseEntity<Map<String , Object>> searchEventByTitle(@RequestParam String title,
    @RequestParam (defaultValue = "0") int page,
    @RequestParam (defaultValue = "10")int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return eventService.searchEventByTitle(title,pageable);
    }

    @GetMapping("/language/{language}")
    public  ResponseEntity<Map<String,Object>> getEventByLanguage(
            @PathVariable String language,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable paging = PageRequest.of(page,size);
        return eventService.getEventByLanguage(language, paging);
    }

    @GetMapping("/cast/{castId}")
    public  ResponseEntity<Map<String,Object>> getEventByCast(
            @PathVariable String castId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable paging = PageRequest.of(page,size);
        return eventService.getEventByCast(castId, paging);
    }

    @GetMapping("/getTitle/{eventId}")
    public String getEventTitle(@PathVariable String eventId){
        return eventService.getEventTitle(eventId);
    }



}
