package com.bms.event_service.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genres")
@Data
@NoArgsConstructor

public class Genre {

    @Id
    private String id;
    private String name;
}