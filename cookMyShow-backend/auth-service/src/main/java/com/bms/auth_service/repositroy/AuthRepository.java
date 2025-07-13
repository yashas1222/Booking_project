package com.bms.auth_service.repositroy;

import com.bms.auth_service.model.Auth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {


    Optional<Auth> findByEmail(@Email(message = "Invalid Email") @NotBlank(message = "Invalid Email") String email);
    void deleteByEmail(String email);

    boolean existsByEmail(@Email(message = "Invalid email") @NotBlank(message = "Invalid email") String email);
}
