package com.travelbee.app.exception;

import com.travelbee.app.dto.response.Message;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<Message> handleMessagingException(MessagingException exception ){
        return new ResponseEntity<>(
                Message.builder().status("Send mail fail").data(exception.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotfoundException.class)
    public ResponseEntity<Message> handleNotFoundException(NotfoundException ex){
        return new ResponseEntity<>(Message.builder().status(ex.getMessage()).build(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AllException.class)
    public ResponseEntity<Message> handleGenerationException(AllException exception){
        return new ResponseEntity<>(
                Message.builder().status("Error Server").data(exception.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
