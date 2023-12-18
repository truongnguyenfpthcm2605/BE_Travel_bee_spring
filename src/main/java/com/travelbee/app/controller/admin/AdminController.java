package com.travelbee.app.controller.admin;

import com.travelbee.app.enities.PlanTour;
import com.travelbee.app.enities.Tour;
import com.travelbee.app.service.impl.AccessServiceImpl;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.OrdersServiceImpl;
import com.travelbee.app.service.impl.TourServiceImpl;
import com.travelbee.app.util.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/staff")
@RequiredArgsConstructor
public class AdminController {
    private final OrdersServiceImpl ordersService;
    private final AccountServiceImpl accountService;
    private final TourServiceImpl tourService;
    private final AccessServiceImpl accessService;

    @GetMapping("/TodayRevenue")
    public ResponseEntity<Object> getTodayRevenue(@RequestParam("today") String today) {
        return new ResponseEntity<>(ordersService.TodayRevenue(Common.stringFormat(today)), HttpStatus.OK);
    }

    @GetMapping("/ticketToday")
    public ResponseEntity<Object> getTicketToday(@RequestParam("today") String today) {
        return new ResponseEntity<>(ordersService.ticketToday(Common.stringFormat(today)), HttpStatus.OK);
    }

    @GetMapping("/countAccount")
    public ResponseEntity<Object> getCountAccount() {
        return new ResponseEntity<>(accountService.countAccount(), HttpStatus.OK);
    }

    @GetMapping("/countTour")
    public ResponseEntity<Object> getCountTour() {
        return new ResponseEntity<>(tourService.countTour(), HttpStatus.OK);
    }

    @GetMapping("/access/linchart/{day}")
    public ResponseEntity<Object> getlineChartAces(@PathVariable("day") Integer day) {
        LocalDate starDate = LocalDate.now().minusDays(day);
        return new ResponseEntity<>(accessService.getLineChart(starDate, PageRequest.of(0, 7)), HttpStatus.OK);
    }

    @GetMapping("/orders/countactive")
    public ResponseEntity<Object> getTicketActive() {
        return new ResponseEntity<>(ordersService.countTicketActive(), HttpStatus.OK);
    }

    @GetMapping("/orders/countuhactive")
    public ResponseEntity<Object> getTicketUhActive() {
        return new ResponseEntity<>(ordersService.countTicketUhActive(), HttpStatus.OK);
    }

    @GetMapping("/orders/moneydaily")
    public ResponseEntity<Object> getLineChartMoney() {
        List<Object[]> list = ordersService.getLineChartMoney();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/tour/trending")
    public ResponseEntity<Object> getTrending() {
        List<Object[]> list = ordersService.getTrending();
        List<Object[]> trend = list.stream().map(e ->
                new Object[]{
                        Common.decimalFormat((Double) e[0])
                        ,e[1]
                        ,Common.dateFormat2(((PlanTour) e[2]).getStardate())
                        ,Common.dateFormat2(((PlanTour) e[2]).getEnddate())
                        ,((PlanTour) e[2]).getTour().getTitle()
                        ,((PlanTour) e[2]).getTour().getViews()
                        ,((PlanTour) e[2]).getTour().getImages()}
        ).toList();

        return new ResponseEntity<>(trend, HttpStatus.OK);
    }






}
