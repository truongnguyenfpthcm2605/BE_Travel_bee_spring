package com.travelbee.app.service;

import com.travelbee.app.enities.Role;
import com.travelbee.app.util.Roles;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role save(Role Role);
    Role update(Role Role);
    Optional<Role> findById(Long id);
    void deleteById(Long id);
    List<Role> findAll();

    Optional<Role> findByName(Roles name);
}
