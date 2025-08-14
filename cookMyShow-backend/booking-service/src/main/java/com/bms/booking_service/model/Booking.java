package com.bms.booking_service.model;


import com.bms.booking_service.dto.BookingRequestDto;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String bookingId;

    private String userEmail;

    private String showId;

    private List<String> seats;

    private double totalAmount;

    private BookingStatus status;

    private LocalDateTime bookingTime;

    public  Booking(BookingRequestDto dto){
        this.userEmail = dto.getUserEmail();
        this.showId = dto.getShowId();
        this.seats = dto.getSeats();
        this.totalAmount = dto.getTotalAmount();
        this.status = BookingStatus.PENDING;
        this.bookingTime = LocalDateTime.now();
    }

}