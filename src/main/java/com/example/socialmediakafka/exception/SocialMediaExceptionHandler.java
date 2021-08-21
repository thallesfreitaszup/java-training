package com.example.socialmediakafka.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SocialMediaExceptionHandler {

    @ExceptionHandler(InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalErrorException(InternalServerError internalServerError){
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), internalServerError.message);
    }

}
