package com.example.socialmediakafka.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private String name;
    private String id;
    private String description;
    private User user;
}
