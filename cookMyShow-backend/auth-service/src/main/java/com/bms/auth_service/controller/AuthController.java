package com.bms.auth_service.controller;

import com.bms.auth_service.dto.AuthLoginRequestDTO;
import com.bms.auth_service.dto.AuthSignupRequestDTO;
import com.bms.auth_service.dto.UserRequestDTO;
import com.bms.auth_service.dto.UserResponseDTO;
import com.bms.auth_service.exception.AuthFailedException;
import com.bms.auth_service.service.AuthService;
import com.bms.auth_service.util.JwtUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    JwtUtil jwtUtil;

@PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody AuthSignupRequestDTO authSignupRequestDTO){
    return authService.signup(authSignupRequestDTO);
}

@PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody AuthLoginRequestDTO authLoginRequestDTO){
    return authService.login(authLoginRequestDTO);
}

@PutMapping("/update/{email}")
public  ResponseEntity<UserResponseDTO> updateUser(@PathVariable String email, @RequestBody UserRequestDTO dto, HttpServletRequest request){
    String authHeader = request.getHeader("Authorization");

    if(authHeader == null || !authHeader.startsWith("Bearer")){
        throw new AuthFailedException("Token required for verification");
    }
    String token = authHeader.substring(7);
    if(!jwtUtil.isTokenValid(token) || !jwtUtil.extractEmail(token).equals(email)){
        throw new AuthFailedException("Invalid token");
    }

    return authService.updateUser(email,dto,token);
}

@DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email, HttpServletRequest request){

    String authHeader = request.getHeader("Authorization");

    if(authHeader == null || !authHeader.startsWith("Bearer")){
        throw new AuthFailedException("Token required for verification");
    }
    String token = authHeader.substring(7);
    if(!jwtUtil.isTokenValid(token) || !jwtUtil.extractEmail(token).equals(email)){
        throw new AuthFailedException("Invalid token");
    }
    return authService.deleteUser(email);
}
}
