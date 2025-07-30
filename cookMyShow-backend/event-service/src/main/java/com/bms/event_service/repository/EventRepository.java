package com.bms.event_service.repository;

import com.bms.event_service.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event,String> {

    Page<Event> findAll(Pageable pageable);

    boolean existsByTitleAndType(String title, String type);

//    List<Event> findByType(String type);

    Page<Event> findByGenreIdsContaining(String genreId, Pageable pageable);

//    List<Event> findByTitleContainingIgnoreCase(String title);

//    Page<Event> findByLanguageContainingIgnoreCase(String language, Pageable pageable);

    Page<Event> findAllByLanguagesContainingIgnoreCase(String language, Pageable paging);

    Page<Event> findByType(String type, Pageable pageable);

    Page<Event> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Event> findAllByCastIdsContaining(String castId, Pageable paging);
}
