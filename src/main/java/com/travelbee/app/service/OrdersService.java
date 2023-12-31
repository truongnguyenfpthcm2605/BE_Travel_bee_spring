package com.travelbee.app.service;

import com.travelbee.app.enities.Orders;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrdersService {
    Orders save(Orders Orders);
    Orders update(Orders Orders);
    Optional<Orders> findById(Long id);
    void deleteById(Long id);
    List<Orders> findAll();

    List<Orders> findticket(String email);
    Double TodayRevenue(Date today);
    Double ticketToday(Date today);

    Long countTicketActive();
    Long countTicketUhActive();

    List<Object[]> getLineChartMoney();

    List<Object[]> getTrending();

    List<Orders[]> getTicketOnTour(Long tourID);

}
