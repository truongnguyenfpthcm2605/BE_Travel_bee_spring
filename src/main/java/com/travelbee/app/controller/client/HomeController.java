package com.travelbee.app.controller.client;

import com.travelbee.app.dto.response.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/home")
@RequiredArgsConstructor
public class HomeController {


    @GetMapping("")
    public ResponseEntity<Message> home(){
        return new ResponseEntity<>(Message.builder().status("Hello").data("Ok").build(), HttpStatus.OK);
    }



}
