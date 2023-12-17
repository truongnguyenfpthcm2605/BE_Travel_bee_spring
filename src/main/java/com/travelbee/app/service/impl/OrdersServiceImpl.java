package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Orders;
import com.travelbee.app.repository.OrdersRepository;
import com.travelbee.app.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "orders")
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    @Override
    @CacheEvict(key = "#order.id", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Orders save(Orders Orders) {
        return ordersRepository.save(Orders);
    }

    @Override
    @CacheEvict(key = "#order.id", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Orders update(Orders Orders) {
        return ordersRepository.save(Orders);
    }

    @Override
    @Cacheable(key = "#id", unless="#result == null")
    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    @CacheEvict(key= "#id", allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
         ordersRepository.deleteById(id);
    }

    @Override
    @Cacheable(unless="#result == null")
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames ="findticket" ,unless="#result == null")
    public List<Orders> findticket(String email) {
        return ordersRepository.findticket(email);
    }

    @Override
    @Cacheable(cacheNames ="TodayRevenue",unless="#result == null")
    public Double TodayRevenue(Date today) {
        return ordersRepository.todayRevenue(today);
    }

    @Override
    @Cacheable(cacheNames ="ticketToday",unless="#result == null")
    public Double ticketToday(Date today) {
        return ordersRepository.ticketToday(today);
    }

    @Override
    @Cacheable(cacheNames ="countTicketActive",unless="#result == null")
    public Long countTicketActive() {
        return ordersRepository.countTicketActive();
    }
    @Override
    @Cacheable(cacheNames ="UhActive",unless="#result == null")
    public Long countTicketUhActive() {
        return ordersRepository.countTicketUhActive();
    }

    @Override
    @Cacheable(cacheNames ="getLineChartMoney",unless="#result == null")
    public List<Object[]> getLineChartMoney() {
        return ordersRepository.getLineChartMoney();
    }

    @Override
    @Cacheable(cacheNames ="getTrending",unless="#result == null")
    public List<Object[]> getTrending() {
        return ordersRepository.getTrending();
    }
}
