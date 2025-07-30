package com.bms.show_service.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class ShowDto {
    private String showId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;
    private List<SeatCategoryDto> seatCategories;
}
