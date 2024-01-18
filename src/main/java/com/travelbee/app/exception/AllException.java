package com.travelbee.app.exception;

import com.travelbee.app.dto.response.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AllException extends Exception{

    public AllException(String message) {
        super(message);
    }
}
