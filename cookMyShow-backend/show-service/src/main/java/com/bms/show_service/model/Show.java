package com.bms.show_service.model;

import com.bms.show_service.dto.ShowRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "shows")
public class Show {

    @Id
    private String id;
    private String eventId;
    private String theatreId;
    private String screenName;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<SeatCategory> seatCategories;
    private ShowStatus  status;

    public  Show(ShowRequestDto dto){
        this.eventId= dto.getEventId();
        this.theatreId = dto.getTheatreId();
        this.screenName = dto.getScreenName();
        this.showDate = dto.getShowDate();
        this.startTime = dto.getStartTime();
        this.endTime = dto.getEndTime();
        this.seatCategories = dto.getSeatCategories();
        this.status = ShowStatus.AVAILABLE;
    }
}
