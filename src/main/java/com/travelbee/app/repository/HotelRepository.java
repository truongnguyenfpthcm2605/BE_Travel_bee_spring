package com.travelbee.app.repository;

import com.travelbee.app.enities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {

    @Query("select o from Hotel  o where o.title like :keyword")
    List<Hotel> findByTitle(@Param("keyword") String title);
}
