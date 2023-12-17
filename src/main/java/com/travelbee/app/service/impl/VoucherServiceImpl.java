package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Voucher;
import com.travelbee.app.repository.VoucherRepository;
import com.travelbee.app.service.VoucherService;
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
@CacheConfig(cacheNames = "vouchers")
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key= "#voucher.id" , allEntries = true)
    public Voucher save(Voucher Voucher) {
        return voucherRepository.save(Voucher);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key= "#voucher.id" , allEntries = true)
    public Voucher update(Voucher Voucher) {
        return voucherRepository.save(Voucher);
    }


    @Override
    @Cacheable(key = "#id",unless="#result == null")
    public Optional<Voucher> findById(String id) {
        return voucherRepository.findById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key ="#id", allEntries = true)
    public void deleteById(String id) {
        voucherRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "voucherTitle", unless = "#result == null")
    public List<Voucher> findByIdOrTitle(String id, String title) {
        return voucherRepository.findByIdOrTitle("%"+id+"%","%"+title+"%");
    }

    @Override
    @Cacheable(cacheNames = "voucherActive",unless="#result == null")
    public List<Voucher> findByActive(Boolean active) {
        return voucherRepository.findByActive(active);
    }
}
