package com.bms.show_service.model;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class SeatCategory {

 private  String categoryName;
 private  int pricePerSeat;
 private int totalSeats;
 private int availableSeats;
 private Map<String, SeatStatus> seatStatusMap;
}
