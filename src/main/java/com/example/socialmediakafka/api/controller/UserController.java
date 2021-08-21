package com.example.socialmediakafka.api.controller;

import com.example.socialmediakafka.api.request.UserRequest;
import com.example.socialmediakafka.api.response.UserResponse;
import com.example.socialmediakafka.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("users")
@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody UserRequest userRequest){
        var user = userRequest.toDomain();
        return this.userService.create(user).toResponse();
    }
}
