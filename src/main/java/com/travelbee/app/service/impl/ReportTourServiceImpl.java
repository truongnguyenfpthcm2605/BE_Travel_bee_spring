package com.travelbee.app.service.impl;

import com.travelbee.app.enities.ReportTour;
import com.travelbee.app.repository.ReportTourRepository;
import com.travelbee.app.service.ReportTourService;
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
@CacheConfig(cacheNames = "reporttours")
public class ReportTourServiceImpl implements ReportTourService {
    private final ReportTourRepository repository;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CacheEvict(key = "#reporttour.id" ,allEntries = true)
    public ReportTour save(ReportTour ReportTour) {
        return repository.save(ReportTour);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CacheEvict(key = "#reporttour.id" ,allEntries = true)
    public ReportTour update(ReportTour ReportTour) {
        return repository.save(ReportTour);
    }


    @Override
    @Cacheable(key = "#id" ,unless="#result == null")
    public Optional<ReportTour> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CacheEvict(key = "#id" ,allEntries = true)
    public void deleteById(Long id) {
         repository.deleteById(id);
    }

    @Override
    @Cacheable
    public List<ReportTour> findAll() {
        return repository.findAll();
    }
}
