package com.travelbee.app.service;

import com.travelbee.app.enities.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    Location save(Location Location);
    Location update(Location Location);
    Optional<Location> findById(Long id);
    void deleteById(Long id);
    List<Location> findAll();
}
