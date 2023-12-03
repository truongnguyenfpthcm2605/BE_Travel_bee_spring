package com.travelbee.app.exception;

import com.travelbee.app.dto.response.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AllException extends   Exception{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Message> getExResponseEntity(){
        return new ResponseEntity<>(Message.builder().status(" System Error!").build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
