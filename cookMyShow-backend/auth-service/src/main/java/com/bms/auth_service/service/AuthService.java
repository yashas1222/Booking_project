package com.bms.auth_service.service;

import com.bms.auth_service.dto.AuthLoginRequestDTO;
import com.bms.auth_service.dto.AuthSignupRequestDTO;
import com.bms.auth_service.dto.UserRequestDTO;
import com.bms.auth_service.dto.UserResponseDTO;
import com.bms.auth_service.exception.AuthFailedException;
import com.bms.auth_service.exception.DownstreamServiceException;
import com.bms.auth_service.exception.UserNotFoundException;
import com.bms.auth_service.feign.UserClient;
import com.bms.auth_service.model.Auth;
import com.bms.auth_service.repositroy.AuthRepository;
import com.bms.auth_service.util.JwtUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    UserClient userClient;

    @Autowired
    JwtUtil jwtUtil;

    public ResponseEntity<String> signup(@Valid AuthSignupRequestDTO authSignupRequestDTO) {
        if (authRepository.existsByEmail(authSignupRequestDTO.getEmail())) {
            throw new UserNotFoundException("User already exists");
        }

        Auth auth = new Auth(authSignupRequestDTO.getEmail(), authSignupRequestDTO.getPassword());
        authRepository.save(auth);
        Long authId = auth.getId();

        UserRequestDTO userRequestDTO = new UserRequestDTO(
                authId,
                authSignupRequestDTO.getName(),
                authSignupRequestDTO.getEmail(),
                authSignupRequestDTO.getPhone(),
                authSignupRequestDTO.getGenreIds()
        );

        try {
            userClient.createUser(userRequestDTO);
        } catch (DownstreamServiceException ex) {
            authRepository.deleteByEmail(authSignupRequestDTO.getEmail());
            throw new RuntimeException("Failed to create user: " + ex.getMessage());
        }

        return new ResponseEntity<>("Signup Successful", HttpStatus.OK);
    }

    public ResponseEntity<UserResponseDTO> login(@Valid AuthLoginRequestDTO authLoginRequestDTO) {
        Auth auth = authRepository.findByEmail(authLoginRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if (!auth.getPassword().equals(authLoginRequestDTO.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }

        String token = jwtUtil.generateToken(auth.getEmail());
        UserResponseDTO userResponseDTO;

        try {
            userResponseDTO = userClient.getUser(authLoginRequestDTO.getEmail()).getBody();
        } catch (DownstreamServiceException ex) {
            throw new RuntimeException("Login failed: " + ex.getMessage());
        }

        userResponseDTO.setToken(token);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    public ResponseEntity<UserResponseDTO> updateUser(String email, UserRequestDTO dto, String token) {
        UserResponseDTO userResponseDTO;

        try {
            userResponseDTO = userClient.updateUser(email, dto).getBody();
        } catch (DownstreamServiceException ex) {
            throw new RuntimeException("Update failed: " + ex.getMessage());
        }

        userResponseDTO.setToken(token);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteUser(String email) {
        authRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        authRepository.deleteByEmail(email);

        try {
            return userClient.deleteUser(email);
        } catch (DownstreamServiceException ex) {
            throw new RuntimeException("Delete failed: " + ex.getMessage());
        }
    }

    public void validateUser(Map<String, String> body) {
        System.out.println("HEREREE");
        if (!jwtUtil.isTokenValid(body.get("token"))) {
            throw new AuthFailedException("User not authenticated");
        }
    }
}