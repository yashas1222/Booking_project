package com.bms.auth_service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

public class Auth {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Invalid Email")
    private  String email;


@NotBlank(message = "Password cant be blank")
@Size(min = 5,message = "Password must Be minimum 5 Characters")
    private String password;

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
