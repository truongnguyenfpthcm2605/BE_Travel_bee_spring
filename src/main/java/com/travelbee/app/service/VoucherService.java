package com.travelbee.app.service;

import com.travelbee.app.enities.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherService {
    Voucher save(Voucher Voucher);
    Voucher update(Voucher Voucher);
    Optional<Voucher> findById(String id);
    void deleteById(String id);
    List<Voucher> findAll();

    List<Voucher> findByIdOrTitle(String id, String title);

    List<Voucher> findByActive(Boolean active);
}
