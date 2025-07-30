package com.bms.show_service.dto;

import com.bms.show_service.model.SeatStatus;
import lombok.Data;

import java.util.Map;

@Data
public class SeatCategoryDto {
    private String categoryName;
    private int pricePerSeat;
    private int availableSeats;
    private Map<String, SeatStatus> seatStatusMap;

}