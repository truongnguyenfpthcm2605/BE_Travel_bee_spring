package com.travelbee.app.controller.client;

import com.travelbee.app.enities.Payment;
import com.travelbee.app.security.payment.PaymentACB;
import com.travelbee.app.service.impl.OrdersServiceImpl;
import com.travelbee.app.service.impl.PaymentServiceImpl;
import com.travelbee.app.util.Banking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;
    private final OrdersServiceImpl ordersService;

    @PostMapping("/save")
    public ResponseEntity<Object> savePayment(@RequestBody PaymentACB paymentACB, @RequestParam("name") String name, @RequestParam("id") Long id) {
        return new ResponseEntity<>(paymentService.save(
                Payment.builder()
                        .name(name)
                        .createdate(new Date())
                        .typepayment(Banking.ACB.name())
                        .money(paymentACB.getAMOUNT())
                        .stk(paymentACB.getDESCRIPTION())
                        .orders(ordersService.findById(id).get()).build()
        ), HttpStatus.OK);
    }
}
