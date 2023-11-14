package com.travelbee.app.service;

import com.travelbee.app.enities.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment save(Payment Payment);
    Payment update(Payment Payment);
    Optional<Payment> findById(Long id);
    void deleteById(Long id);
    List<Payment> findAll();
}
