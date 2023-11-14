package com.travelbee.app.service;

import com.travelbee.app.enities.PlanTour;

import java.util.List;
import java.util.Optional;

public interface PlanTourService {
    PlanTour save(PlanTour PlanTour);
    PlanTour update(PlanTour PlanTour);
    Optional<PlanTour> findById(Long id);
    void deleteById(Long id);
    List<PlanTour> findAll();
}
