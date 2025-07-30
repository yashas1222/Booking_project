package com.bms.event_service.service;

import com.bms.event_service.dto.CastRequestDto;
import com.bms.event_service.dto.EventRequestDto;
import com.bms.event_service.dto.EventResponseDto;
import com.bms.event_service.exception.EvenNotFoundException;
import com.bms.event_service.model.Cast;
import com.bms.event_service.model.Event;
import com.bms.event_service.model.Genre;
import com.bms.event_service.repository.CastRepository;
import com.bms.event_service.repository.EventRepository;
import com.bms.event_service.repository.GenreRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CastRepository castRepository;

    @Autowired
    GenreRepository genreRepository;

    public ResponseEntity<String> createEvent(@Valid EventRequestDto dto) {

        List<CastRequestDto> castRequestDtos = dto.getCasts();
        List<String> genres = dto.getGenres();
        Set<String> castIds = new HashSet<>();
        Set<String> genreIds = new HashSet<>();

        for(CastRequestDto castRequestDto : castRequestDtos){
       Optional<Cast> cast = castRepository.findByNameIgnoreCase(castRequestDto.getName());
       if(cast.isPresent()){
        castIds.add(cast.get().getId());
       }else{
           Cast newCast = new Cast(castRequestDto);
           castRepository.save(newCast);
           castIds.add(newCast.getId());
       }
        }

        for(String genre : genres){
                Optional<Genre> genre1 = genreRepository.findByNameIgnoreCase(genre);
                if(genre1.isPresent()){
                    genreIds.add(genre1.get().getId());
                }else{
                    Genre newGenre = new Genre();
                    newGenre.setName(genre);
                   genreRepository.save(newGenre);
                    castIds.add(newGenre.getId());
            }

        }
        Event event = new Event(dto,genreIds,castIds);

        if(!eventRepository.existsByTitleAndType(event.getTitle(), event.getType())){
            eventRepository.save(event);
            return new ResponseEntity<>("Added new Event", HttpStatus.OK);
        }
        return new ResponseEntity<>("Event already exists",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> createEvents(List<EventRequestDto> dtos) {
        for(EventRequestDto dto : dtos){
            createEvent(dto);
        }
        return  new ResponseEntity<>("Successfully created Events",HttpStatus.OK);
    }

    public List<EventResponseDto> fillCastGenreDetails(List<Event> events){

        Set<String> allCastsIds = events.stream()
                .flatMap(e -> e.getCastIds().stream())
                .collect(Collectors.toSet());

        Set<String> allGenreIds = events.stream()
                .flatMap(e -> e.getGenreIds().stream())
                .collect(Collectors.toSet());

        List<Cast> casts = castRepository.findAllById(allCastsIds);
        List<Genre> genres = genreRepository.findAllById(allGenreIds);

        Map<String, Cast> castMap = casts.stream()
                .collect(Collectors.toMap(Cast::getId, Function.identity()));

        Map<String, Genre> genreMap = genres.stream()
                .collect(Collectors.toMap(Genre::getId, Function.identity()));


        List<EventResponseDto> responseDtos =
                events.stream()
                        .map(event -> {
                            List<Cast> casts1 = event.getCastIds().stream()
                                    .map(castMap::get)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toList());

                            List<Genre> genres1 = event.getGenreIds()
                                    .stream()
                                    .map(genreMap::get)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toList());

                            return new EventResponseDto(event,casts,genres);
                        }).collect(Collectors.toList());

        return responseDtos;
    }

    private  ResponseEntity<Map<String, Object>> createResponseForPageable(Page<Event> events){

        if(events.isEmpty()){
            return ResponseEntity.ok(new HashMap<>());
        }

        List<EventResponseDto> eventResponseDtos = fillCastGenreDetails(events.getContent());

        Map<String, Object> response = new HashMap<>();
        response.put("events", eventResponseDtos);
        response.put("currentPage" , events.getNumber());
        response.put("lastPage" , events.getTotalPages() - 1);
        response.put("totalEvents", events.getTotalElements());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> getAllEvents(Pageable paging) {
        Page<Event> events = eventRepository.findAll(paging);
        return createResponseForPageable(events);
    }

    public ResponseEntity<EventResponseDto> getEvent(String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(EvenNotFoundException::new);
        List<Cast> casts = castRepository.findAllById(event.getCastIds());
        List<Genre> genres = genreRepository.findAllById(event.getGenreIds());

        EventResponseDto eventResponseDto = new EventResponseDto(event,casts,genres);

        return ResponseEntity.ok(eventResponseDto);

    }

    public ResponseEntity<EventResponseDto> updateEvent(@Valid EventRequestDto dto, String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(EvenNotFoundException::new);

        List<CastRequestDto> castRequestDtos = dto.getCasts();
        List<String> genresRequestDtos = dto.getGenres();
        Set<String> castIds = new HashSet<>();
        Set<String> genreIds = new HashSet<>();

        for(CastRequestDto castRequestDto : castRequestDtos){
            Optional<Cast> cast = castRepository.findByNameIgnoreCase(castRequestDto.getName());
            if(cast.isPresent()){
                castIds.add(cast.get().getId());
            }else{
                Cast newCast = new Cast(castRequestDto);
                castRepository.save(newCast);
                castIds.add(newCast.getId());
            }
        }

        for(String genre : genresRequestDtos){
            Optional<Genre> genre1 = genreRepository.findByNameIgnoreCase(genre);
            if(genre1.isPresent()){
                genreIds.add(genre1.get().getId());
            }else{
                Genre newGenre = new Genre();
                newGenre.setName(genre);
                genreRepository.save(newGenre);
                castIds.add(newGenre.getId());
            }

        }

        event.setTitle(dto.getTitle());
        event.setType(dto.getType());
        event.setLanguages(dto.getLanguages());
        event.setDuration(dto.getDuration());
        event.setGenreIds(new ArrayList<>(genreIds));
        event.setPosterUrl(dto.getPosterUrl());
        event.setTrailerUrl(dto.getTrailerUrl());
        event.setDescription(dto.getDescription());
        event.setAgeRating(dto.getAgeRating());
        event.setReleaseDate(dto.getReleaseDate());
        event.setRating(dto.getRating());
        event.setCastIds(new ArrayList<>(castIds));

        eventRepository.save(event);

        List<Cast> casts = castRepository.findAllById(event.getCastIds());
        List<Genre> genres = genreRepository.findAllById(event.getGenreIds());
        EventResponseDto eventResponseDto = new EventResponseDto(event, casts, genres);

        return new ResponseEntity<>(eventResponseDto,HttpStatus.OK);

    }

    public ResponseEntity<String> deleteEvent(String eventId) {
        eventRepository.findById(eventId).orElseThrow(EvenNotFoundException::new);
        eventRepository.deleteById(eventId);
        return  new ResponseEntity<>("Event Deleted succesfully", HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getEventsByType(String type, Pageable pageable) {
        Page<Event> events = eventRepository.findByType(type, pageable);
      return createResponseForPageable(events);

    }

    public ResponseEntity<Map<String, Object>> getEventsByGenre(String genreId , Pageable pageable) {
        Page<Event> events = eventRepository.findByGenreIdsContaining(genreId, pageable);
return createResponseForPageable(events);

    }

    public ResponseEntity<Map<String , Object>> searchEventByTitle(String title, Pageable pageable) {
        Page<Event> events = eventRepository.findByTitleContainingIgnoreCase(title, pageable);
        return createResponseForPageable(events);
    }

    public ResponseEntity<Map<String , Object>> getEventByLanguage(String language,  Pageable paging) {
       Page<Event> events = eventRepository.findAllByLanguagesContainingIgnoreCase(language , paging);
return  createResponseForPageable(events);
    }

    public ResponseEntity<Map<String, Object>> getEventByCast(String castId, Pageable paging) {
   Page<Event> events = eventRepository.findAllByCastIdsContaining(castId,paging);
   return createResponseForPageable(events);
    }

    public String getEventTitle(String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(EvenNotFoundException::new);
       return event.getTitle();

    }
}
