package com.travelbee.app.service.impl;

import com.travelbee.app.enities.PlanTour;
import com.travelbee.app.repository.PlanTourRepository;
import com.travelbee.app.service.PlanTourService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class PlanTourServiceImpl implements PlanTourService {

    private final PlanTourRepository planTourRepository;
    @Override
    public PlanTour save(PlanTour PlanTour) {
        return planTourRepository.save(PlanTour);
    }

    @Override
    public PlanTour update(PlanTour PlanTour) {
        return planTourRepository.save(PlanTour);
    }

    @Override
    public Optional<PlanTour> findById(Long id) {
        return planTourRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
         planTourRepository.deleteById(id);
    }

    @Override
    public List<PlanTour> findAll() {
        return planTourRepository.findAll();
    }

    @Override
    public Optional<List<PlanTour>> findByPlanTour(Long tourID) {
        return planTourRepository.findByPlanTour(tourID);
    }
}
