package com.bms.event_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CastRequestDto {
    @NotBlank(message = "Cast name is required")
    private String name;

    private String imageUrl;

    private String bio;
}

