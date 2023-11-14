package com.travelbee.app.service;

import com.travelbee.app.enities.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    Orders save(Orders Orders);
    Orders update(Orders Orders);
    Optional<Orders> findById(Long id);
    void deleteById(Long id);
    List<Orders> findAll();
}
