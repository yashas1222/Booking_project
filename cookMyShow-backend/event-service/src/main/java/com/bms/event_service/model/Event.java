package com.bms.event_service.model;


import com.bms.event_service.dto.EventRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Document(collection = "events")
@CompoundIndexes({
        @CompoundIndex(name = "unique_event_index" , def = "{'title' : 1, 'type' : 1, 'releaseDate' : 1}", unique = true)
})

@Data
@NoArgsConstructor
public class Event {
    @Id
    private String id;

    private String title;

    private String type;

    private List<String> languages;

    private int duration;

    private List<String> genreIds;

    private String posterUrl;

    private  String trailerUrl;

    private String description;

    private String ageRating;

    private LocalDate releaseDate;

    private double rating;


    private List<String> castIds;

    public Event(EventRequestDto dto,Set<String> genreIds, Set<String> castIds ) {
        this.title = dto.getTitle();
        this.type = dto.getType();
        this.languages = dto.getLanguages();
        this.duration = dto.getDuration();
        this.genreIds = new ArrayList<>(genreIds);
        this.posterUrl = dto.getPosterUrl();
        this.trailerUrl = dto.getTrailerUrl();
        this.description = dto.getDescription();
        this.ageRating = dto.getAgeRating();
        this.releaseDate = dto.getReleaseDate();
        this.rating = dto.getRating();
        this.castIds = new ArrayList<>(castIds);
    }


}
