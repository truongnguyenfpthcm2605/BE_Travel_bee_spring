package com.travelbee.app.service;

import com.travelbee.app.enities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account save(Account account);
    Account update(Account account);
    Optional<Account> findById(Long id);
    void deleteById(Long id);
    List<Account> findAll();

    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    Optional<Account> checkLogin(String email, String password);

    Optional<Account> findByUsernameAndProviderID(String username, String providerID);

    Integer countAccount();

}
