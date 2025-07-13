package com.bms.auth_service.controller;

import com.bms.auth_service.dto.AuthLoginRequestDTO;
import com.bms.auth_service.dto.AuthSignupRequestDTO;
import com.bms.auth_service.dto.UserResponseDTO;
import com.bms.auth_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    AuthService authService;

@PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody AuthSignupRequestDTO authSignupRequestDTO){
    return authService.signup(authSignupRequestDTO);
}

@PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody AuthLoginRequestDTO authLoginRequestDTO){
    return authService.login(authLoginRequestDTO);

}
}
