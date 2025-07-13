package com.bms.auth_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthLoginRequestDTO {
    @Email(message = "Invalid Email")
    @NotBlank(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password required")
    private String password;
}
