package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Location;
import com.travelbee.app.repository.LocationRepository;
import com.travelbee.app.service.LocationService;
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
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Location save(Location Location) {
        return locationRepository.save(Location);
    }

    @Override
    public Location update(Location Location) {
        return locationRepository.save(Location);
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAllByIsActiveTrue();
    }
}
