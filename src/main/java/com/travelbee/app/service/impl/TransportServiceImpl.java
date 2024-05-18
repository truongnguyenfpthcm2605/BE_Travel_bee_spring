package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Transport;
import com.travelbee.app.repository.TransportRepository;
import com.travelbee.app.service.TransportService;
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
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;

    @Override
    public Transport save(Transport Transport) {
        return transportRepository.save(Transport);
    }

    @Override
    public Transport update(Transport Transport) {
        return transportRepository.save(Transport);
    }


    @Override
    public Optional<Transport> findById(Long id) {
        return transportRepository.findById(id);
    }

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
