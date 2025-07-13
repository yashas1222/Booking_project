package com.bms.user_service.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class User {
@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

   private String  name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private LocalDateTime  createdAt;

@PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }




    public User(Long id,String name, String email, String phone) {
    this.id = id;
    this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
