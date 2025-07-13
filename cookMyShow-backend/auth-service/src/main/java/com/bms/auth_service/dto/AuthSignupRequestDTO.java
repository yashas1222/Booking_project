package com.bms.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class AuthSignupRequestDTO {

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

    @NotBlank(message = "Password cant be blank")
    @Size(min = 5,message = "Password must Be minimum 5 Characters")
    private String password;


}
