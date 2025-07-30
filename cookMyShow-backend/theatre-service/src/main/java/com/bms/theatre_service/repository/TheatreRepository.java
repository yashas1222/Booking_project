package com.bms.theatre_service.repository;

import com.bms.theatre_service.model.Theatre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TheatreRepository extends MongoRepository<Theatre, String> {

    Page<Theatre> findByCityIgnoreCase(String city, Pageable pageable);

    List<Theatre> findAllByIdAndCity(List<String> theatreIds, String city);

    List<Theatre> findAllByIdInAndCityIgnoreCase(List<String> theatreIds, String city);
}
