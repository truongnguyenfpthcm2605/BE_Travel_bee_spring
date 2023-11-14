package com.travelbee.app.repository;

import com.travelbee.app.enities.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport,Long> {
}
