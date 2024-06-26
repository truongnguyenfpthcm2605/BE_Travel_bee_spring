package com.travelbee.app.repository;

import com.travelbee.app.enities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("select o from Location o where o.isactive = true order by o.createdate")
    List<Location> findAllByIsActiveTrue();
}
