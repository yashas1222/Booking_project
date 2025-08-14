package com.bms.booking_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {
    @Email(message = "Not valid Email")
    private String userEmail;

    @NotNull(message = "Show ID cannot be null")
    private String showId;

    @NotEmpty(message = "Seat numbers cannot be empty")
    @Size(min = 1, message = "At least one seat must be selected")
    private List<String> seats;

    @Min(value = 1, message = "Total amount must be greater than 0")
    private double totalAmount;
}
