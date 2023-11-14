package com.travelbee.app.repository;

import com.travelbee.app.enities.Tourdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourDetailsRepository extends JpaRepository<Tourdetails,Long> {
}
