package com.bms.auth_service.service;

import com.bms.auth_service.dto.AuthLoginRequestDTO;
import com.bms.auth_service.dto.AuthSignupRequestDTO;
import com.bms.auth_service.dto.UserRequestDTO;
import com.bms.auth_service.dto.UserResponseDTO;
import com.bms.auth_service.exception.UserNotFoundException;
import com.bms.auth_service.feign.AuthInterface;
import com.bms.auth_service.model.Auth;
import com.bms.auth_service.repositroy.AuthRepository;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    AuthInterface authInterface;

    public ResponseEntity<String> signup(@Valid AuthSignupRequestDTO authSignupRequestDTO) {

        Auth auth = new Auth(authSignupRequestDTO.getEmail(),authSignupRequestDTO.getPassword());
authRepository.save(auth);
Long authId = auth.getId();
        UserRequestDTO userRequestDTO = new UserRequestDTO(authId,authSignupRequestDTO.getName(),authSignupRequestDTO.getEmail(),authSignupRequestDTO.getPhone(),authSignupRequestDTO.getGenreIds());
authInterface.createUser(userRequestDTO);
return new ResponseEntity<>("Signup Successfull",HttpStatus.OK);

    }

    public ResponseEntity<UserResponseDTO> login(@Valid AuthLoginRequestDTO authLoginRequestDTO) {
        Auth auth = authRepository.findByEmail(authLoginRequestDTO.getEmail()).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        return authInterface.getUser(authLoginRequestDTO.getEmail());
    }
}
