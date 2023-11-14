package com.travelbee.app.service;

import com.travelbee.app.enities.ReportTour;

import java.util.List;
import java.util.Optional;

public interface ReportTourService {
    ReportTour save(ReportTour ReportTour);
    ReportTour update(ReportTour ReportTour);
    Optional<ReportTour> findById(Long id);
    void deleteById(Long id);
    List<ReportTour> findAll();
}
