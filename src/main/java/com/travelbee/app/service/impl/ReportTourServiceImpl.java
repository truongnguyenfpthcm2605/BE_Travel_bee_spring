package com.travelbee.app.service.impl;

import com.travelbee.app.enities.ReportTour;
import com.travelbee.app.repository.ReportTourRepository;
import com.travelbee.app.service.ReportTourService;
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
public class ReportTourServiceImpl implements ReportTourService {
    private final ReportTourRepository repository;
    @Override
    public ReportTour save(ReportTour ReportTour) {
        return repository.save(ReportTour);
    }

    @Override
    public ReportTour update(ReportTour ReportTour) {
        return repository.save(ReportTour);
    }


    @Override
    public Optional<ReportTour> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
         repository.deleteById(id);
    }

    @Override
    public List<ReportTour> findAll() {
        return repository.findAll();
    }
}
