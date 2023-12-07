package com.travelbee.app.service;

import com.travelbee.app.enities.PlanTour;
import com.travelbee.app.enities.Tour;
import com.travelbee.app.enities.Tourdetails;

import java.util.List;
import java.util.Optional;

public interface TourService {
    Tour save(Tour Tour);
    Tour update(Tour Tour);
    Optional<Tour> findById(Long id);
    void deleteById(Long id);
    List<Tour> findAll();

    List<Tour> saveAll(List<Tour> tours);

    List<Tour> findTourOutstanding();

    Integer countTour();




}
