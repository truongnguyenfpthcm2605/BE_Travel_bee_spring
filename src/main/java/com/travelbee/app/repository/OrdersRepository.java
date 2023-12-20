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

    @Query("select count(o) , count(o.isactive) from Orders  o where o.isactive= false ")
    Long countTicketUhActive();
    @Query("select count(o) , count(o.isactive) from Orders  o where o.isactive= true ")
    Long countTicketActive();

    @Query("select  CAST(o.createdate AS DATE), sum(o.price) from Orders " +
            "o group by CAST(o.createdate AS DATE) order by CAST(o.createdate AS DATE) asc ")
    List<Object[]> getLineChartMoney();

    @Query("SELECT SUM(o.price), CAST(o.createdate AS DATE), o.plantour FROM Orders o " +
            "GROUP BY CAST(o.createdate AS DATE), o.plantour ORDER BY CAST(o.createdate AS DATE)")
    List<Object[]> getTrending();

    @Query("select o from Orders o where o.plantour.tour.id = :tourID ")
    List<Orders[]> getTicketOnTour( @Param("tourID") Long tourID);

}
