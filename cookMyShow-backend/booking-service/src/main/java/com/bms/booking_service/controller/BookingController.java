package com.bms.booking_service.controller;


import com.bms.booking_service.dto.BookingRequestDto;
import com.bms.booking_service.model.Booking;
import com.bms.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bookings")
public class BookingController {

    @Autowired
BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequestDto request) {
        return bookingService.createBooking(request);
    }

}
