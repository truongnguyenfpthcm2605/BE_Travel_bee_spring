package com.travelbee.app.repository;

import com.travelbee.app.enities.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface AccessRepository extends JpaRepository<Access,Long> {
    Optional<Access> findByAccessdate(LocalDate accessdate);

}
