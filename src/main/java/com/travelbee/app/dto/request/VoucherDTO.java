package com.travelbee.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VoucherDTO {
    private String id;
    private String title;
    private String image;
    private Double discount;
    private Double condition;
    private Integer quanity;
    private Date createdate;
    private Date enddate;
    private String email;
}
