package com.travelbee.app.controller.admin;

import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.OrdersServiceImpl;
import com.travelbee.app.service.impl.TourServiceImpl;
import com.travelbee.app.util.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/v1/staff")
@RequiredArgsConstructor
public class AdminController {
    private final OrdersServiceImpl ordersService;
    private final AccountServiceImpl accountService;
    private final TourServiceImpl tourService;

    @GetMapping("/TodayRevenue")
    public ResponseEntity<Object> getTodayRevenue(@RequestParam("today") String today){
        return new ResponseEntity<>(ordersService.TodayRevenue(Common.stringFormat(today)), HttpStatus.OK);
    }

    @GetMapping("/ticketToday")
    public ResponseEntity<Object> getTicketToday(@RequestParam("today") String today){
        return new ResponseEntity<>(ordersService.ticketToday(Common.stringFormat(today)), HttpStatus.OK);
    }

    @GetMapping("/countAccount")
    public ResponseEntity<Object> getCountAccount(){
        return new ResponseEntity<>(accountService.countAccount(), HttpStatus.OK);
    }
    @GetMapping("/countTour")
    public ResponseEntity<Object> getCountTour(){
        return new ResponseEntity<>(tourService.countTour(), HttpStatus.OK);
    }
}
