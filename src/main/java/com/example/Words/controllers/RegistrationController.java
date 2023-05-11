package com.example.Words.controllers;

import com.example.Words.dto.CreateUserRequest;
import com.example.Words.dto.CreateUserResponse;
import com.example.Words.entities.User;
import com.example.Words.services.UserService;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateUserResponse tryToCreateUser(@RequestBody CreateUserRequest request) {

        User user = userService.createUser(request.getUsername(), request.getPassword());
        CreateUserResponse response = new CreateUserResponse();
        response.setUsername(user.getUsername());
        return response;

    }
}
