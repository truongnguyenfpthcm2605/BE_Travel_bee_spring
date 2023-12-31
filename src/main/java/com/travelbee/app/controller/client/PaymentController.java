package com.travelbee.app.controller.client;

import com.travelbee.app.enities.Payment;
import com.travelbee.app.service.impl.OrdersServiceImpl;
import com.travelbee.app.service.impl.PaymentServiceImpl;
import com.travelbee.app.util.Banking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;
    private final OrdersServiceImpl ordersService;

    @PostMapping("/save")
    public ResponseEntity<Object> savePayment(
            @RequestParam("name") String name,
            @RequestParam("id") Long id,
            @RequestParam("money") Double money
            ,@RequestParam("content") String content) {
        return new ResponseEntity<>(paymentService.save(
                Payment.builder()
                        .name(name)
                        .createdate(new Date())
                        .typepayment(Banking.ACB.name())
                        .money(money)
                        .stk("N/A")
                        .content(content)
                        .orders(ordersService.findById(id).get()).build()
        ), HttpStatus.OK);
    }
}
