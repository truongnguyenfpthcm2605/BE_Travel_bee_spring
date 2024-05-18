package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Tourdetails;
import com.travelbee.app.repository.TourDetailsRepository;
import com.travelbee.app.service.TourDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class TourDetailsServiceImpl implements TourDetailsService {
    private final TourDetailsRepository tourDetailsRepository;
    @Override
    public Tourdetails save(Tourdetails Tourdetails) {
        return tourDetailsRepository.save(Tourdetails) ;
    }

    @Override
    public Tourdetails update(Tourdetails Tourdetails) {
        return tourDetailsRepository.save(Tourdetails) ;
    }

    @Override
    public Optional<Tourdetails> findById(Long id) {
        return tourDetailsRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        tourDetailsRepository.deleteById(id);
    }

    @Override
    public List<Tourdetails> findAll() {
        return tourDetailsRepository.findAll();
    }

    @Override
    public Optional<List<Tourdetails>> findByTourDetails(Long tourID) {
        return tourDetailsRepository.findTourDetailsByTourId(tourID);
    }
}
