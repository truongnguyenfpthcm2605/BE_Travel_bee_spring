package com.travelbee.app.service;

import com.travelbee.app.enities.Tourdetails;

import java.util.List;
import java.util.Optional;

public interface TourDetailsService {
    Tourdetails save(Tourdetails Tourdetails);
    Tourdetails update(Tourdetails Tourdetails);
    Optional<Tourdetails> findById(Long id);
    void deleteById(Long id);
    List<Tourdetails> findAll();
    Optional<List<Tourdetails>> findByTourDetails(Long tourID);
}
