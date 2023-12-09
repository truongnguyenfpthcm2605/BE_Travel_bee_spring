package com.travelbee.app.repository;

import com.travelbee.app.enities.Access;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccessRepository extends JpaRepository<Access,Long> {
    Optional<Access> findByAccessdate(LocalDate accessdate);

    @Query("SELECT a.accessdate, a.accesscount FROM Access a WHERE a.accessdate >= :startDate ORDER BY a.accessdate ASC ")
    List<Object[]> findLatest7Days(@Param("startDate") LocalDate startDate, Pageable pageable);
}
