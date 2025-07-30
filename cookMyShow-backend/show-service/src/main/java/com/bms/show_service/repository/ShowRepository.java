package com.bms.show_service.repository;

import com.bms.show_service.model.Show;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Repository
public interface ShowRepository extends MongoRepository<Show, String> {
    boolean existsByEventIdAndTheatreIdAndScreenNameAndShowDateAndStartTime(@NotBlank(message = "Event Id is required") String eventId, @NotBlank(message = "Theater Id is required") String theatreId, @NotBlank(message = "Screen Id is required") String screenId, @NotNull(message = "Show Date is required") LocalDate showDate, @NotNull(message = "Show Start Time is required") LocalTime startTime);

    List<Show> findByTheatreId(String theatreId);

    List<Show> findByEventIdAndShowDate(String eventId, LocalDate date);

    List<Show> findByEventIdAndTheatreIdAndShowDate(String eventId, String theatreId, LocalDate date);
}
