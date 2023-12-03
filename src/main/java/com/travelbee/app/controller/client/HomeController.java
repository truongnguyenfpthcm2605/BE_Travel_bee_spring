package com.travelbee.app.controller.client;

import com.travelbee.app.dto.response.Message;
import com.travelbee.app.enities.Tour;
import com.travelbee.app.model.mail.MailerServiceImpl;
import com.travelbee.app.service.impl.TourServiceImpl;
import com.travelbee.app.service.impl.VoucherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/home")
@RequiredArgsConstructor
public class HomeController {

    private final TourServiceImpl tourService;
    private final VoucherServiceImpl voucherService;
    private final MailerServiceImpl mailerService;
    @GetMapping("")
    public ResponseEntity<Message> home(){
        return new ResponseEntity<>(Message.builder().status("Hello").data("Ok").build(), HttpStatus.OK);
    }

    @GetMapping("/tourOutstanding")
    public ResponseEntity<Object> findTourOutstanding(){
        return new ResponseEntity<>(tourService.findTourOutstanding().subList(0,6),HttpStatus.OK);
    }

    @GetMapping("/voucher/{id}")
    public ResponseEntity<Object> findVoucher(@PathVariable("id") String id){
        return voucherService.findById(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




}
