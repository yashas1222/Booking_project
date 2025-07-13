package com.bms.user_service.service;

import com.bms.user_service.dto.UserRequestDTO;
import com.bms.user_service.dto.UserResponseDTO;
import com.bms.user_service.exception.UserNotFoundException;
import com.bms.user_service.model.User;
import com.bms.user_service.model.UserGenrePreference;
import com.bms.user_service.repository.UserGenrePreferenceRepository;
import com.bms.user_service.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGenrePreferenceRepository userGenrePreferenceRepository;

    public ResponseEntity<String> createUser(@Valid UserRequestDTO userRequestDTO) {
        User user = new User(userRequestDTO.getId(),userRequestDTO.getName(), userRequestDTO.getEmail(), userRequestDTO.getPhone());
        userRepository.save(user);
        Long userId = user.getId();
        Set<Long> genreIds = new HashSet<>(userRequestDTO.getGenreIds());

        for (Long genreId : genreIds) {
            UserGenrePreference userGenrePreference = new UserGenrePreference(userId, genreId);
            userGenrePreferenceRepository.save(userGenrePreference);
        }


        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


    public ResponseEntity<UserResponseDTO> getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found!"));

        Long userId = user.getId();
       List<Long> genreIds =  userGenrePreferenceRepository.findGenreListByUserId(userId);



       UserResponseDTO userResponseDTO = new UserResponseDTO(user.getName(),user.getEmail(),user.getPhone(),user.getCreatedAt(),genreIds);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK
        );
    }

//    public ResponseEntity<String> updateUser(@Valid UserRequestDTO userRequestDTO) {
//        User user = userRepository.findByEmail(userRequestDTO.getEmail()).orElseThrow(()-> new UserNotFoundException("User not found!"));
//        user.setName(userRequestDTO.getName());
//        user.setEmail(userRequestDTO.getEmail());
//        user.setPhone(userRequestDTO.getPhone());
//
//    }
}