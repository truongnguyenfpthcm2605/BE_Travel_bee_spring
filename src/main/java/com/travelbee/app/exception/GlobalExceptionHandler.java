package com.travelbee.app.exception;

import com.travelbee.app.dto.response.Message;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<Message> handleMessagingException(Message message , HttpStatus httpStatus){
        return new ResponseEntity<>(message,httpStatus);
    }
}
