package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Transport;
import com.travelbee.app.repository.TransportRepository;
import com.travelbee.app.service.TransportService;
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
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Transport save(Transport Transport) {
        return transportRepository.save(Transport);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Transport update(Transport Transport) {
        return transportRepository.save(Transport);
    }


    @Override
    public Optional<Transport> findById(Long id) {
        return transportRepository.findById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public void deleteById(Long id) {
        transportRepository.deleteById(id);
    }


    @Override
    public List<Transport> findAll() {
        return transportRepository.findAll();
    }

    @Override
    public List<Transport> saveAll(List<Transport> list) {
        return transportRepository.saveAll(list);
    }
}