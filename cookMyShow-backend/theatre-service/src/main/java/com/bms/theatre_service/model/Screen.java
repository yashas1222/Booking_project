package com.bms.theatre_service.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.rmi.MarshalException;

@Data
@NoArgsConstructor
public class Screen {

    @NotBlank(message = "Screen name is required")
    private String name;

    @NotBlank(message = "Type of screen is mandatory")
    private String screenType;

    @NotBlank(message = "Number of seats available in the screen should be mentioned")
    private int totalSeats;
}
