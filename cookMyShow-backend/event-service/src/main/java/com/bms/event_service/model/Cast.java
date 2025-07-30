package com.bms.event_service.model;

import com.bms.event_service.dto.CastRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "casts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cast {

    @Id
    private String id;

    private String name;

    private String imageUrl;

    private String bio;

    public Cast(CastRequestDto castDto) {
        this.name = castDto.getName();
        this.imageUrl = castDto.getImageUrl();
        this.bio = castDto.getBio();
    }
}
