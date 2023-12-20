package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Account;
import com.travelbee.app.repository.AccountRepository;
import com.travelbee.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "accounts")
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key = "#account.id", allEntries = true)
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key = "#account.id", allEntries = true)
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Cacheable(key = "#id" ,unless="#result == null")
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key= "#id", allEntries = true)
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    @Cacheable(unless="#result == null")
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    @Cacheable(unless="#result == null")
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    @Cacheable(unless="#result == null")
    public Optional<Account> checkLogin(String email, String password) {
        return accountRepository.checkLogin(email,password);
    }

    @Override
    @Cacheable(unless="#result == null")
    public Optional<Account> findByUsernameAndProviderID(String username, String providerID) {
        return accountRepository.findByUsernameAndProviderID(username,providerID);
    }

    @Override
    @Cacheable
    public Integer countAccount() {
        return accountRepository.countAccount();
    }
}
