package com.bms.theatre_service.dto;

import com.bms.theatre_service.model.Theatre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheatreInfoDto {
    private String theatreId;
    private String name;
    private String address;

    public  TheatreInfoDto(Theatre theatre){
        this.theatreId = theatre.getId();
        this.name = theatre.getName();
        this.address = theatre.getAddress();
    }


}

