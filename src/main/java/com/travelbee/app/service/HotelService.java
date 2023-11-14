package com.travelbee.app.service;

import com.travelbee.app.enities.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    Hotel save(Hotel Hotel);
    Hotel update(Hotel Hotel);
    Optional<Hotel> findById(Long id);
    void deleteById(Long id);
    List<Hotel> findAll();

    List<Hotel> findByTitle(String title);

    List<Hotel> saveAll(List<Hotel> hotels);
}
