package com.travelbee.app.security.userprincal;

import com.travelbee.app.enities.Account;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final AccountServiceImpl accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Account not found"));
        return new UserPrinciple(account);
    }


}
