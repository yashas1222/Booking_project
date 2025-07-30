package com.bms.event_service.dto;

import com.bms.event_service.model.Cast;
import com.bms.event_service.model.Event;
import com.bms.event_service.model.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class EventResponseDto {

    private String id;

    private String title;

    private String type;

    private List<String> languages;

    private int duration;


    private List<Genre> genres;

    private String posterUrl;

    private  String trailerUrl;

    private String description;

    private String ageRating;

    private LocalDate releaseDate;

    private double rating;


    private List<Cast> casts;

    public EventResponseDto(Event event, List<Cast> casts, List<Genre> genres ) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.type = event.getType();
        this.languages = event.getLanguages();
        this.duration = event.getDuration();
        this.genres = genres;
        this.posterUrl = event.getPosterUrl();
        this.trailerUrl = event.getTrailerUrl();
        this.description = event.getDescription();
        this.ageRating = event.getAgeRating();
        this.releaseDate = event.getReleaseDate();
        this.rating = event.getRating();
        this.casts = casts;
    }


}
