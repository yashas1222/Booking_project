package com.bms.event_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventRequestDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Type is required")
    private String type;

    @NotEmpty(message = "Language is required")
    private List<String> languages;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int duration;

    @NotEmpty(message = "At least one genre is required")
    private List<String> genres;

    private String posterUrl;

    private  String trailerUrl;

    private String description;

    @NotBlank(message = "Age Rating is Mandatory")
    private String ageRating;

    private LocalDate releaseDate;

    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
    @DecimalMax(value = "10.0", message = "Rating cannot exceed 10")
    private double rating;

    @NotEmpty(message = "Cast list cannot be empty")
    private List<CastRequestDto> casts;

}
