package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Voucher;
import com.travelbee.app.repository.VoucherRepository;
import com.travelbee.app.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;

    @Override
    public Voucher save(Voucher Voucher) {
        return voucherRepository.save(Voucher);
    }

    @Override
    public Voucher update(Voucher Voucher) {
        return voucherRepository.save(Voucher);
    }


    @Override
    public Optional<Voucher> findById(String id) {
        return voucherRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public List<Voucher> findByIdOrTitle(String id, String title) {
        return voucherRepository.findByIdOrTitle("%"+id+"%","%"+title+"%");
    }

    @Override
    public List<Voucher> findByActive(Boolean active) {
        return voucherRepository.findByActive(active);
    }
}
