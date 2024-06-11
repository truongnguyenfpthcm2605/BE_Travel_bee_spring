package com.travelbee.app.repository;

import com.travelbee.app.enities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {

    @Query("select o from Tour  o order by o.views desc ")
    List<Tour> findTourOutstanding();

    @Query("select count(o) from Tour  o")
    Integer countTour();

    @Query("select o from Tour o where o.isactive =true  order by o.createdate desc ")
    List<Tour> findAllActive();



}
