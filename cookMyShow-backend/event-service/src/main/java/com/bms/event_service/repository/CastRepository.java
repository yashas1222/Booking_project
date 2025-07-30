package com.bms.event_service.repository;

import com.bms.event_service.model.Cast;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CastRepository extends MongoRepository<Cast,String> {

    Optional<Cast> findByNameIgnoreCase(@NotBlank(message = "Cast name is required") String name);
}
