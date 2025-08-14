package com.bms.show_service.dto;

import com.bms.show_service.model.ShowStatus;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class ShowDto {
    private String showId;
    private LocalTime startTime;
    private LocalTime endTime;
    private ShowStatus status;
    private List<SeatCategoryDto> seatCategories;
}
