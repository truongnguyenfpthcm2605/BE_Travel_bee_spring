package com.travelbee.app.service.impl;

import com.travelbee.app.enities.PlanTour;
import com.travelbee.app.repository.PlanTourRepository;
import com.travelbee.app.service.PlanTourService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "plantours")
public class PlanTourServiceImpl implements PlanTourService {

    private final PlanTourRepository planTourRepository;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public PlanTour save(PlanTour PlanTour) {
        return planTourRepository.save(PlanTour);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public PlanTour update(PlanTour PlanTour) {
        return planTourRepository.save(PlanTour);
    }

    @Override
    @Cacheable(key = "#id" ,unless="#result == null")
    public Optional<PlanTour> findById(Long id) {
        return planTourRepository.findById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
         planTourRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public List<PlanTour> findAll() {
        return planTourRepository.findAll();
    }

    @Override
    public Optional<List<PlanTour>> findByPlanTour(Long tourID) {
        return planTourRepository.findByPlanTour(tourID);
    }
}
