package com.bms.booking_service.service;

import com.bms.booking_service.dto.BookingRequestDto;
import com.bms.booking_service.exception.DownstreamServiceException;
import com.bms.booking_service.feign.ShowClient;
import com.bms.booking_service.feign.UserClient;
import com.bms.booking_service.model.Booking;
import com.bms.booking_service.repository.BookingRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserClient userClient;

    @Autowired
    ShowClient showClient;


    public ResponseEntity<Booking> createBooking(BookingRequestDto request) {


        try{
             userClient.isUser(request.getUserEmail());
            Map<String, List<String>> seatsValidation = showClient.validateBooking(request.getShowId(), request.getSeats()).getBody();
            if(!seatsValidation.get("unavailableSeats").isEmpty()){
                throw new RuntimeException(seatsValidation.get("unavailableSeats").toString() +" these seats are not available");
            }
        } catch (DownstreamServiceException e) {
            throw new RuntimeException(e.getMessage());
        }

        Booking booking = new Booking(request);
        bookingRepository.save(booking);
        return new ResponseEntity<>(booking,HttpStatus.OK);
    }
}
