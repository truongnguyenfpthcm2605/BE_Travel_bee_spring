package com.travelbee.app.repository;

import com.travelbee.app.enities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository  extends JpaRepository<Account,Long> {


    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    Optional<Account> findByUsernameAndProviderID(String username, String providerID);

    @Query("select o from Account o where o.email = :email and o.password = :password ")
    Optional<Account> checkLogin(@Param("email") String email,@Param("password") String password);

    @Query("select count(o) from Account  o ")
    Integer countAccount();
}
