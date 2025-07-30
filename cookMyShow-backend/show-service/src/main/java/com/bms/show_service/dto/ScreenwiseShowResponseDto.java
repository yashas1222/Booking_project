package com.bms.show_service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ScreenwiseShowResponseDto {
    private String eventId;
    private String eventTitle;
    private String theatreId;
    private String theatreName;
    private LocalDate date;
    private List<ScreenShowsDto> screens;
}
