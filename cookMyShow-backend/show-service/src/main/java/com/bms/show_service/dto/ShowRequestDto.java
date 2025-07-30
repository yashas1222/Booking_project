package com.bms.show_service.dto;

import com.bms.show_service.model.SeatCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ShowRequestDto {

    @NotBlank(message = "Event Id is required")
    private String eventId;

    @NotBlank(message = "Theater Id is required")
    private String theatreId;

    @NotBlank(message = "Screen Id is required")
    private String screenName;

    @NotNull(message = "Show Date is required")
    private LocalDate showDate;

    @NotNull(message = "Show Start Time is required")
    private LocalTime startTime;

    @NotNull(message = "Show End Time is required")
    private LocalTime endTime;

    @NotEmpty(message = "Atleast One Category of Seats should be listed")
    private List<SeatCategory> seatCategories;

    @NotBlank(message = "Show Status is required")
    private String status = "ACTIVE";
}
