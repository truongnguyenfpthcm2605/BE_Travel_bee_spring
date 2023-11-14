package com.travelbee.app.repository;

import com.travelbee.app.enities.Role;
import com.travelbee.app.util.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(Roles name);
}
