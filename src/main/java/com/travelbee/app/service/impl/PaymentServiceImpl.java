package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Payment;
import com.travelbee.app.repository.PaymentRepository;
import com.travelbee.app.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "payments")
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key = "#payment.id", allEntries = true)
    public Payment save(Payment Payment) {
        return paymentRepository.save(Payment);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key = "#payment.id", allEntries = true)
    public Payment update(Payment Payment) {
        return paymentRepository.save(Payment);
    }

    @Override
    @Cacheable(key = "#id", unless="#result == null")
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key= "#id", allEntries = true)
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    @Cacheable(unless="#result == null")
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}
