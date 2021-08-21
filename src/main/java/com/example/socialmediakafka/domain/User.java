package com.example.socialmediakafka.domain;

import com.example.socialmediakafka.api.response.UserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private Integer age;
    private String city;
    private String email;
    private String password;
    private User[] friends;

    public User(String id, String name, Integer age, String city, String email, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.email = email;
        this.password = password;
    }

    public UserResponse toResponse() {
        return new UserResponse(this.id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", friends=" + Arrays.toString(friends) +
                '}';
    }
}
