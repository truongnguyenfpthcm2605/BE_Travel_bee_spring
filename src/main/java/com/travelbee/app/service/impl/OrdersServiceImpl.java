package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Orders;
import com.travelbee.app.repository.OrdersRepository;
import com.travelbee.app.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Orders save(Orders Orders) {
        return ordersRepository.save(Orders);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Orders update(Orders Orders) {
        return ordersRepository.save(Orders);
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
         ordersRepository.deleteById(id);
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public List<Orders> findticket(String email) {
        return ordersRepository.findticket(email);
    }

    @Override
    public Double TodayRevenue(Date today) {
        return ordersRepository.todayRevenue(today);
    }

    @Override
    public Double ticketToday(Date today) {
        return ordersRepository.ticketToday(today);
    }

    @Override
    public Long countTicketActive() {
        return ordersRepository.countTicketActive();
    }
    @Override
    public Long countTicketUhActive() {
        return ordersRepository.countTicketUhActive();
    }

    @Override
    public List<Object[]> getLineChartMoney() {
        return ordersRepository.getLineChartMoney();
    }

    @Override
    public List<Object[]> getTrending() {
        return ordersRepository.getTrending();
    }

    @Override
    public List<Orders[]> getTicketOnTour(Long tourID) {
        return ordersRepository.getTicketOnTour(tourID);
    }
}
