package com.travelbee.app.service;

import com.travelbee.app.enities.Transport;

import java.util.List;
import java.util.Optional;

public interface TransportService {
    Transport save(Transport Transport);
    Transport update(Transport Transport);
    Optional<Transport> findById(Long id);
    void deleteById(Long id);
    List<Transport> findAll();

    List<Transport> saveAll(List<Transport> list);
}
