package com.bms.show_service.dto;

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
}
