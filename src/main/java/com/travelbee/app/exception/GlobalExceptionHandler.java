package com.travelbee.app.exception;

import com.travelbee.app.dto.response.Message;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<Message> handleMessagingException(Message message , HttpStatus httpStatus){
        return new ResponseEntity<>(message,httpStatus);
    }

    @ExceptionHandler(NotfoundException.class)
    public ResponseEntity<Message> handleNotFoundException(NotfoundException ex, WebRequest request){
        return new ResponseEntity<>(Message.builder().status(ex.getMessage()).build(),HttpStatus.NOT_FOUND);
    }
}
