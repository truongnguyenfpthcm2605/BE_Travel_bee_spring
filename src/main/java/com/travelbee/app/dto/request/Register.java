package com.travelbee.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Register {
    private String username;
    private String email;
    private String fullname;
    private String password;
    private Date birthday ;
    private String roles;
}
