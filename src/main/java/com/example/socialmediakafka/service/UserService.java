package com.example.socialmediakafka.service;

import com.example.socialmediakafka.exception.InternalServerError;
import com.example.socialmediakafka.repository.UserRepository;
import com.example.socialmediakafka.domain.User;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return this.userRepository.create(user).orElseThrow(
                () ->  new InternalServerError("Error creating user")
        );
    }

}
