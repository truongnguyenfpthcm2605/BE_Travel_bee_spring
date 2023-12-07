package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Access;
import com.travelbee.app.repository.AccessRepository;
import com.travelbee.app.service.AccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "access")
public class AccessServiceImpl  implements AccessService {
    private final AccessRepository accessRepository;
    @Override
    public Access save(Access access) {
        return accessRepository.save(access);
    }

    @Override
    public Optional<Access> findById(Long id) {
        return accessRepository.findById(id);
    }

    @Override
    @Cacheable
    public List<Access> findAll() {
        return accessRepository.findAll();
    }

    @Override
    public Optional<Access> findByAccessdate(LocalDate accessdate) {
        return accessRepository.findByAccessdate(accessdate);
    }
}
