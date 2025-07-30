package com.bms.auth_service.service;

import com.bms.auth_service.dto.AuthLoginRequestDTO;
import com.bms.auth_service.dto.AuthSignupRequestDTO;
import com.bms.auth_service.dto.UserRequestDTO;
import com.bms.auth_service.dto.UserResponseDTO;
import com.bms.auth_service.exception.AuthFailedException;
import com.bms.auth_service.exception.UserNotFoundException;
import com.bms.auth_service.feign.AuthInterface;
import com.bms.auth_service.model.Auth;
import com.bms.auth_service.repositroy.AuthRepository;
import com.bms.auth_service.util.JwtUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    AuthInterface authInterface;

    @Autowired
    JwtUtil jwtUtil;

    public ResponseEntity<String> signup(@Valid AuthSignupRequestDTO authSignupRequestDTO) {
        if(authRepository.existsByEmail(authSignupRequestDTO.getEmail())){
            throw new UserNotFoundException("User already exists");
        }

        Auth auth = new Auth(authSignupRequestDTO.getEmail(),authSignupRequestDTO.getPassword());
authRepository.save(auth);
Long authId = auth.getId();
        UserRequestDTO userRequestDTO = new UserRequestDTO(authId,authSignupRequestDTO.getName(),authSignupRequestDTO.getEmail(),authSignupRequestDTO.getPhone(),authSignupRequestDTO.getGenreIds());
authInterface.createUser(userRequestDTO);
return new ResponseEntity<>("Signup Successfull",HttpStatus.OK);

    }

    public ResponseEntity<UserResponseDTO> login(@Valid AuthLoginRequestDTO authLoginRequestDTO) {
        Auth auth = authRepository.findByEmail(authLoginRequestDTO.getEmail()).orElseThrow(()-> new UserNotFoundException("User Not Found"));

        if(!auth.getPassword().equals(authLoginRequestDTO.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        String token = jwtUtil.generateToken(auth.getEmail());
UserResponseDTO userResponseDTO= authInterface.getUser(authLoginRequestDTO.getEmail()).getBody();
userResponseDTO.setToken(token);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }

    public ResponseEntity<UserResponseDTO> updateUser(String email, UserRequestDTO dto,String token) {
    UserResponseDTO userResponseDTO = authInterface.updateUser(email,dto).getBody();
    userResponseDTO.setToken(token);
    return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }
@Transactional
    public ResponseEntity<String> deleteUser(String email) {
        authRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found!"));
        authRepository.deleteByEmail(email);
    return authInterface.deleteUser(email);
    }

    public void validateUser(Map<String, String> body) {
         if(!jwtUtil.isTokenValid(body.get("token"))){
             throw new AuthFailedException("User not authenticated");
         }
    }
}
