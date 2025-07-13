package com.bms.auth_service.feign;


import com.bms.auth_service.dto.UserRequestDTO;
import com.bms.auth_service.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("USER-SERVICE")
public interface AuthInterface {

    @PostMapping("user/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO);

    @GetMapping("user/get/{email}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String email);
}