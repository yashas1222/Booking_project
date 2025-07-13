package com.bms.user_service.controller;

import com.bms.user_service.dto.UserRequestDTO;
import com.bms.user_service.dto.UserResponseDTO;
import com.bms.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return userService.createUser(userRequestDTO);
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String email){
        return userService.getUser(email);
    }

//    @PutMapping("/update/{email}")
//    public ResponseEntity<String> updateUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
//        return userService.updateUser(userRequestDTO);
//    }

}
