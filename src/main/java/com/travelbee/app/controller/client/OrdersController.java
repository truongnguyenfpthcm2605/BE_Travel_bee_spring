package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.OrdersDTO;
import com.travelbee.app.enities.Orders;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.OrdersServiceImpl;
import com.travelbee.app.service.impl.PlanTourServiceImpl;
import com.travelbee.app.util.QRcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersServiceImpl ordersService;
    private final AccountServiceImpl accountService;
    private final PlanTourServiceImpl planTourService;
    private final QRcodeService qRcodeService;
    private static final String QR_CODE_TICKET = "http://localhost:8080/checkTikect";


    @PostMapping("/save")
    public ResponseEntity<Object> saveTicket(@RequestBody OrdersDTO ordersDTO) {
        return new ResponseEntity<>(ordersService.save(
                Orders.builder()
                        .voucher(ordersDTO.getVoucher())
                        .qrcode(qRcodeService.generationQRcode(ordersDTO.getQrcode()))
                        .status(ordersDTO.getStatus())
                        .cccd(ordersDTO.getCccd())
                        .email(ordersDTO.getEmail())
                        .numberphone(ordersDTO.getNumberphone())
                        .price(ordersDTO.getPrice())
                        .member(ordersDTO.getMember())
                        .createdate(new Date())
                        .isactive(true)
                        .account(accountService.findByEmail(ordersDTO.getEmailAccount()).get())
                        .plantour(planTourService.findById(ordersDTO.getPlanTourId()).get()).build()), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id){
        return ordersService.findById(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable("id") Long id){
        Optional<Orders> orders = ordersService.findById(id);
        if(orders.isPresent()){
            orders.get().setIsactive(false);
            return new ResponseEntity<>(ordersService.update(orders.get()),HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/history/{email}")
    public ResponseEntity<Object> findAllHistoryTicket(@PathVariable("email") String email){
        return new ResponseEntity<>(ordersService.findticket(email),HttpStatus.OK);
    }
}
