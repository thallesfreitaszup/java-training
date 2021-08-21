package com.example.socialmediakafka.api.request;

import com.example.socialmediakafka.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Getter
@Setter
public class UserRequest {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @Positive
    private Integer age;

    public UserRequest(String name, String email, String password, String city, Integer age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.age = age;
    }

    public User toDomain() {
        return new User(
                UUID.randomUUID().toString(),
                this.name,
                this.age,
                this.city,
                this.email,
                this.password
        );
    }
}
