package com.travelbee.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountResponse {
    private String fullName;
    private String email;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
}
