package com.bms.event_service.repository;

import com.bms.event_service.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends MongoRepository<Genre,String> {


    boolean existsByName(String genreName);

    Optional<Genre> findByNameIgnoreCase(String genre);
}
