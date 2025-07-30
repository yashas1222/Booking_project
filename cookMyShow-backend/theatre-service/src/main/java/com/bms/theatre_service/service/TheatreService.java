package com.bms.theatre_service.service;

import com.bms.theatre_service.dto.TheatreInfoDto;
import com.bms.theatre_service.dto.TheatreRequestDto;
import com.bms.theatre_service.model.Theatre;
import com.bms.theatre_service.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TheatreService {
@Autowired
    TheatreRepository theatreRepository;

public ResponseEntity<Theatre> createTheatre(TheatreRequestDto theatreRequestDto) {
Theatre theatre = new Theatre(theatreRequestDto);
    theatreRepository.save(theatre);
    return new ResponseEntity<>(theatre, HttpStatus.OK);
    }

    public ResponseEntity<List<Theatre>> createBulkTheatre(List<TheatreRequestDto> theatreRequestDtos) {
        List<Theatre> theatres = theatreRequestDtos.stream()
                .map(Theatre::new)
                .toList();

        theatreRepository.saveAll(theatres);
        return new ResponseEntity<>(theatres, HttpStatus.OK);

}

    private  ResponseEntity<Map<String, Object>> createResponseForPageable(Page<Theatre> theatres){

        if(theatres.isEmpty()){
            return ResponseEntity.ok(new HashMap<>());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("events", theatres.getContent());
        response.put("currentPage" , theatres.getNumber());
        response.put("lastPage" , theatres.getTotalPages() - 1);
        response.put("totalEvents", theatres.getTotalElements());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> getTheatres(Pageable pageable) {
    Page<Theatre> theatres = theatreRepository.findAll(pageable);
    return createResponseForPageable(theatres);

    }

    public ResponseEntity<TheatreInfoDto> getTheatre(String theatreId) {
    Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(RuntimeException::new);
   TheatreInfoDto theatreInfoDto = new TheatreInfoDto(theatre);
    return new ResponseEntity<>(theatreInfoDto, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getTheatresByCity(String city, Pageable pageable) {
Page<Theatre> theatres = theatreRepository.findByCityIgnoreCase(city,pageable);
return createResponseForPageable(theatres);
}


    public ResponseEntity<List<TheatreInfoDto>> getTheatresByIdAndCity( String city, List<String> theatreIds) {
        List<Theatre> theatres = theatreRepository.findAllByIdInAndCityIgnoreCase(theatreIds, city);
        List<TheatreInfoDto> dtos = theatres.stream()
                .map(TheatreInfoDto::new)
                .toList();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
}


}

