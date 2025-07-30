package com.bms.show_service.dto;

import com.bms.show_service.model.SeatCategory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ShowResponseDto {

    private String id;

    private String eventId;
    private String theatreId;
    private String screenName;

    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private List<SeatCategory> seatCategories;

    private String status = "ACTIVE";
}
