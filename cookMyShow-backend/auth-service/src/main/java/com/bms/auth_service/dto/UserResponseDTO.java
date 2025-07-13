package com.bms.auth_service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponseDTO {

    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private List<Long> genreIds;


    public UserResponseDTO() {}

    public UserResponseDTO(String name, String email, String phone, LocalDateTime createdAt, List<Long> genreIds) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
        this.genreIds = genreIds;
    }
}
