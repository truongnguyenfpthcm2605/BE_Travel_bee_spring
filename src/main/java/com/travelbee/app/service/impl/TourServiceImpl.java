package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Tour;
import com.travelbee.app.repository.TourRepository;
import com.travelbee.app.service.TourService;
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
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    @Override
    public Tour save(Tour Tour) {
        return tourRepository.save(Tour);
    }

    @Override
    public Tour update(Tour Tour) {
        return tourRepository.save(Tour);
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        tourRepository.deleteById(id);
    }

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public List<Tour> saveAll(List<Tour> tours) {
        return tourRepository.saveAll(tours);
    }

    @Override
    public List<Tour> findTourOutstanding() {
        return tourRepository.findTourOutstanding();
    }

    @Override
    public Integer countTour() {
        return tourRepository.countTour();
    }


}
