package com.bms.auth_service.feign;


import com.bms.auth_service.config.FeignConfig;
import com.bms.auth_service.dto.UserRequestDTO;
import com.bms.auth_service.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name ="USER-SERVICE", configuration = FeignConfig.class)
public interface UserClient {

    @PostMapping("user/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO);

    @GetMapping("user/get/{email}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String email);

    @PutMapping("user/update/{email}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String email, @RequestBody UserRequestDTO dto) ;

    @DeleteMapping("user/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email);
}