package com.travelbee.app.repository;

import com.travelbee.app.enities.PlanTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanTourRepository extends JpaRepository<PlanTour,Long> {

    @Query("select o from PlanTour o where o.tour.id = :id ")
    Optional<List<PlanTour>> findByPlanTour(@Param("id") Long tourID);
}
