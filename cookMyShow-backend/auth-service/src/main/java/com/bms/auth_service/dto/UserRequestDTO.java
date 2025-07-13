package com.bms.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
public class UserRequestDTO {
    Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Invalid email")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phone;

    @Size(min = 1, message = "At least one genre must be selected")
    private List<Long> genreIds;

    public  UserRequestDTO(){

    }


    public UserRequestDTO(Long id,String name, String email, String phone, List<Long> genreIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.genreIds = genreIds;
    }
}

