package com.bms.show_service.service;

import com.bms.show_service.dto.*;
import com.bms.show_service.fiegn.EventClient;
import com.bms.show_service.fiegn.TheatreClient;
import com.bms.show_service.model.SeatCategory;
import com.bms.show_service.model.SeatStatus;
import com.bms.show_service.model.Show;
import com.bms.show_service.repository.ShowRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    TheatreClient theatreClient;

   @Autowired
    EventClient eventClient;

    public ResponseEntity<Show> createShow(@Valid ShowRequestDto showRequestDto) {

        if(showRepository.existsByEventIdAndTheatreIdAndScreenNameAndShowDateAndStartTime(
                showRequestDto.getEventId(),showRequestDto.getTheatreId(),showRequestDto.getScreenName(), showRequestDto.getShowDate(),showRequestDto.getStartTime()
        )){
                throw new RuntimeException("No slot for show here");
        }

        Show show = new Show(showRequestDto);

        for (SeatCategory category : show.getSeatCategories()) {
            String categoryPrefix = category.getCategoryName(); // e.g., "Gold", "Silver"
            Map<String, SeatStatus> seatStatusMap = new HashMap<>();

            for (int i = 1; i <= category.getTotalSeats(); i++) {
                String seatLabel = categoryPrefix + i; // e.g., Gold1, Gold2
                seatStatusMap.put(seatLabel, SeatStatus.AVAILABLE);
            }

            category.setSeatStatusMap(seatStatusMap);
            category.setAvailableSeats(category.getTotalSeats()); // Optional but useful
        }


        showRepository.save(show);
        return new ResponseEntity<>(show, HttpStatus.OK);
    }

    public List<Show> createBulkShows(@Valid List<ShowRequestDto> showRequestDtos) {
        List<Show> savedShows = new ArrayList<>();

        for (ShowRequestDto dto : showRequestDtos) {
            boolean exists = showRepository.existsByEventIdAndTheatreIdAndScreenNameAndShowDateAndStartTime(
                    dto.getEventId(),
                    dto.getTheatreId(),
                    dto.getScreenName(),
                    dto.getShowDate(),
                    dto.getStartTime()
            );
            if (exists) {
                continue;
            }

            Show show = new Show(dto);

            for (SeatCategory category : show.getSeatCategories()) {
                Map<String, SeatStatus> seatStatusMap = new HashMap<>();
                String prefix = category.getCategoryName(); // e.g., "Gold"

                for (int i = 1; i <= category.getTotalSeats(); i++) {
                    String seatLabel = prefix + i; // e.g., Gold1, Gold2, etc.
                    seatStatusMap.put(seatLabel, SeatStatus.AVAILABLE);
                }

                category.setSeatStatusMap(seatStatusMap);
                category.setAvailableSeats(category.getTotalSeats());
            }

            savedShows.add(showRepository.save(show));
        }

        return savedShows;
    }



    public ResponseEntity<Show> getShow(String showId) {
Show show = showRepository.findById(showId).orElseThrow(()-> new RuntimeException("Show not found"));
return new ResponseEntity<>(show, HttpStatus.OK);

    }

    public ResponseEntity<List<Show>> getAllShows() {
        List<Show> shows = showRepository.findAll();
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }

    public ResponseEntity<List<Show>> getShowsForTheatre(String theatreId) {
List<Show> shows = showRepository.findByTheatreId(theatreId);
return new ResponseEntity<>(shows, HttpStatus.OK);
    }

    public ResponseEntity<ShowAvailabilityResponseDto> getTheatersWithShowsAvailable(String eventId, String city, LocalDate date) {
    List<Show> shows = showRepository.findByEventIdAndShowDate(eventId, date);
    Set<String> theaterIds =  shows.stream()
            .map(Show::getTheatreId)
            .collect(Collectors.toSet());
    if(theaterIds.isEmpty()){
        return new ResponseEntity<>(new ShowAvailabilityResponseDto(),HttpStatus.OK);

    }
    List<TheatreInfoDto> theatreInfoDtos = theatreClient.getTheatresByIdAndCity(city, new ArrayList<>(theaterIds)).getBody();

    ShowAvailabilityResponseDto responseDto = new ShowAvailabilityResponseDto();
responseDto.setEventId(eventId);
responseDto.setEventTitle(eventClient.getEventTitle(eventId));
responseDto.setCity(city);
responseDto.setDate(date);
responseDto.setTheatres(theatreInfoDtos);


    return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    public ResponseEntity<ScreenwiseShowResponseDto> getShowsForTheatreOnDate(String eventId, String theatreId, LocalDate date) {
        List<Show> shows = showRepository.findByEventIdAndTheatreIdAndShowDate(eventId, theatreId, date);

        if (shows.isEmpty()) {
            return ResponseEntity.ok(new ScreenwiseShowResponseDto());
        }

        String eventTitle = eventClient.getEventTitle(eventId);
        TheatreInfoDto theatre = theatreClient.getTheatre(theatreId).getBody();

        Map<String, List<Show>> screenShowMap = shows.stream()
                .collect(Collectors.groupingBy(Show::getScreenName));

        List<ScreenShowsDto> screenShowsList = new ArrayList<>();

        for (Map.Entry<String, List<Show>> entry : screenShowMap.entrySet()) {
            List<ShowDto> showDtos = entry.getValue().stream().map(show -> {

                List<SeatCategoryDto> seatCategoryDtos = show.getSeatCategories().stream().map(category -> {
                    SeatCategoryDto dto = new SeatCategoryDto();
                    dto.setCategoryName(category.getCategoryName());
                    dto.setPricePerSeat(category.getPricePerSeat());
                    dto.setAvailableSeats(category.getAvailableSeats());
                    dto.setSeatStatusMap(category.getSeatStatusMap());
                    return dto;
                }).collect(Collectors.toList());

                ShowDto showDto = new ShowDto();
                showDto.setShowId(show.getId());
                showDto.setStartTime(show.getStartTime());
                showDto.setEndTime(show.getEndTime());
                showDto.setStatus(show.getStatus());
                showDto.setSeatCategories(seatCategoryDtos);
                return showDto;

            }).collect(Collectors.toList());

            ScreenShowsDto screenShowsDto = new ScreenShowsDto();
            screenShowsDto.setScreenName(entry.getKey());
            screenShowsDto.setShows(showDtos);
            screenShowsList.add(screenShowsDto);
        }

        ScreenwiseShowResponseDto response = new ScreenwiseShowResponseDto();
        response.setEventId(eventId);
        response.setEventTitle(eventTitle);
        response.setTheatreId(theatreId);
        response.setTheatreName(theatre.getName());
        response.setDate(date);
        response.setScreens(screenShowsList);

        return ResponseEntity.ok(response);
    }

}
