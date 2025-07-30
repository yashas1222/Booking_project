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
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcmNAZ21haWwuY29tIiwiaWF0IjoxNzUzMjA0MDY2LCJleHAiOjE3NTMyMjIwNjZ9.SZy_n66_T5RKI6KlLu7zTBFDIYRR_9gZaA8R2dxFkzs