package com.travelbee.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String email;
    private String fullname;
    private Date birthday;
    private Date createdate;
    private Date updatedate;
    private String image;
}
