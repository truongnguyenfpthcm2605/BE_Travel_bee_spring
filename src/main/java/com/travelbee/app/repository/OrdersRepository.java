package com.travelbee.app.repository;

import com.travelbee.app.enities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    @Query("select  o from Orders o where o.account.email = :email and o.isactive = true order by o.createdate desc ")
    List<Orders> findticket( @Param("email") String email);

    @Query("SELECT SUM(o.price) FROM Orders o WHERE CAST(o.createdate AS DATE) = CAST(:today AS DATE)")
    Double todayRevenue(@Param("today") Date today);
    @Query("SELECT COUNT(o.id) FROM Orders o WHERE CAST(o.createdate AS DATE) = CAST(:today AS DATE)")
    Double ticketToday(Date today);

}
