package com.travelbee.app.repository;

import com.travelbee.app.enities.Tourdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourDetailsRepository extends JpaRepository<Tourdetails,Long> {

    Optional<List<Tourdetails>> findTourDetailsByTourId(Long tourId);

}
