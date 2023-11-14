package com.travelbee.app.repository;

import com.travelbee.app.enities.PlanTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanTourRepository extends JpaRepository<PlanTour,Long> {
}
