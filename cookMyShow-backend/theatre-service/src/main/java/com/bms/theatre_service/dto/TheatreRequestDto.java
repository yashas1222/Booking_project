package com.bms.theatre_service.dto;

import com.bms.theatre_service.model.Screen;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;


@Data

public class TheatreRequestDto {



    @NotBlank(message = "Theatre name is mandatory")
    private String name;

    @NotBlank(message = "City in which the theatre belongs is mandatory")
    private String city;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Pincode is mandatory")
    private String pincode;
    private String contactEmail;
    private String contactPhone;

    @NotEmpty(message = "At-least one screen should be listed for a theatre")
    private List<Screen> screens;

}
