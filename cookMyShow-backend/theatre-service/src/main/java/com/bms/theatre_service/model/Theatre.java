package com.bms.theatre_service.model;

import com.bms.theatre_service.dto.TheatreRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "theatres")
public class Theatre {

    @Id
    private String id;

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

    public  Theatre(TheatreRequestDto dto){
        this.name = dto.getName();
        this.city = dto.getCity();
        this.address = dto.getAddress();
        this.pincode = dto.getPincode();
        this.contactEmail = dto.getContactEmail();
        this.contactPhone = dto.getContactPhone();
        this.screens = dto.getScreens();
    }
}
