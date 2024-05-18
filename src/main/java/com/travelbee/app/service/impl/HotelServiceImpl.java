package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Hotel;
import com.travelbee.app.repository.HotelRepository;
import com.travelbee.app.service.HotelService;
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
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel save(Hotel Hotel) {
        return hotelRepository.save(Hotel);
    }

    @Override
    public Hotel update(Hotel Hotel) {
        return hotelRepository.save(Hotel);
    }
    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
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
