package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Location;
import com.travelbee.app.repository.LocationRepository;
import com.travelbee.app.service.LocationService;
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
@CacheConfig(cacheNames = "locations")
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Location save(Location Location) {
        return locationRepository.save(Location);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Location update(Location Location) {
        return locationRepository.save(Location);
    }

    @Override
    @Cacheable(key = "#id" ,unless="#result == null")
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
