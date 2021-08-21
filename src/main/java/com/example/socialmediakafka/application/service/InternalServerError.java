package com.example.socialmediakafka.application.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternalServerError extends RuntimeException {
    public String message;
    public InternalServerError(String message) {
        this.message = message;
    }
}
