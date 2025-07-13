package com.bms.user_service.service;

import com.bms.user_service.dto.UserRequestDTO;
import com.bms.user_service.dto.UserResponseDTO;
import com.bms.user_service.exception.UserNotFoundException;
import com.bms.user_service.model.User;
import com.bms.user_service.model.UserGenrePreference;
import com.bms.user_service.repository.UserGenrePreferenceRepository;
import com.bms.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
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


    @Transactional
    public ResponseEntity<UserResponseDTO> updateUser(String email, UserRequestDTO dto) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found"));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        userGenrePreferenceRepository.deleteByUserId(user.getId());

        for (Long genreId : dto.getGenreIds()) {
            UserGenrePreference userGenrePreference = new UserGenrePreference(user.getId(), genreId);
            userGenrePreferenceRepository.save(userGenrePreference);
        }
        List<Long> genreIds =  userGenrePreferenceRepository.findGenreListByUserId(user.getId());

        userRepository.save(user);
        return new ResponseEntity<>(new UserResponseDTO(user.getName(),user.getEmail(), user.getPhone(),user.getCreatedAt(),genreIds ),HttpStatus.OK);
    }

@Transactional
    public ResponseEntity<String> deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found"));
        userRepository.deleteById(user.getId());
        return new ResponseEntity<>("Deleted user "+user.getEmail(),HttpStatus.OK);
    }
}