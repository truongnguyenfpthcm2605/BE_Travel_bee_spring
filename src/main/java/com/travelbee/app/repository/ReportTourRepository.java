package com.travelbee.app.repository;

import com.travelbee.app.enities.ReportTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportTourRepository extends JpaRepository<ReportTour,Long> {
}
