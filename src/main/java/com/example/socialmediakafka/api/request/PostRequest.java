package com.example.socialmediakafka.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private String name;
    private String description;
    private String userId;

}
