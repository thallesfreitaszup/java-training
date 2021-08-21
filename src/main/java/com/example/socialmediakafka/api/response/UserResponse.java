package com.example.socialmediakafka.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private String id;

    public UserResponse(String id) {
        this.id = id;
    }
}
