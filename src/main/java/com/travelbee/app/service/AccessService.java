package com.travelbee.app.service;

import com.travelbee.app.enities.Access;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccessService {
     Access save(Access access);
     Optional<Access> findById(Long id);
     List<Access> findAll();

     Optional<Access> findByAccessdate(LocalDate accessdate);

     List<Object[]> getLineChart(LocalDate date, Pageable pageable);


}
