package com.bms.show_service.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowAvailabilityResponseDto {
    private String eventId;
    private String eventTitle;
    private String city;
    private LocalDate date;
    private List<TheatreInfoDto> theatres;
}
