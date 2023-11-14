package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Hotel;
import com.travelbee.app.repository.HotelRepository;
import com.travelbee.app.service.HotelService;
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
@CacheConfig( cacheNames = "hotels" )
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key ="#hotel.id", allEntries = true)
    public Hotel save(Hotel Hotel) {
        return hotelRepository.save(Hotel);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key ="#hotel.id", allEntries = true)
    public Hotel update(Hotel Hotel) {
        return hotelRepository.save(Hotel);
    }
    @Override
    @Cacheable(key = "#id")
    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key ="#id", allEntries = true)
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> findByTitle(String title) {
        return hotelRepository.findByTitle("%"+title+"%");
    }

    @Override
    public List<Hotel> saveAll(List<Hotel> hotels) {
        return hotelRepository.saveAll(hotels);
    }
}
