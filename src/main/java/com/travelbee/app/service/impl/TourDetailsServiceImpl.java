package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Tourdetails;
import com.travelbee.app.repository.TourDetailsRepository;
import com.travelbee.app.service.TourDetailsService;
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
@CacheConfig(cacheNames = "tourdetails")
public class TourDetailsServiceImpl implements TourDetailsService {
    private final TourDetailsRepository tourDetailsRepository;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)

    public Tourdetails save(Tourdetails Tourdetails) {
        return tourDetailsRepository.save(Tourdetails) ;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)

    public Tourdetails update(Tourdetails Tourdetails) {
        return tourDetailsRepository.save(Tourdetails) ;
    }

    @Override
    @Cacheable(key = "#id" ,unless="#result == null")
    public Optional<Tourdetails> findById(Long id) {
        return tourDetailsRepository.findById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        tourDetailsRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public List<Tourdetails> findAll() {
        return tourDetailsRepository.findAll();
    }
}
