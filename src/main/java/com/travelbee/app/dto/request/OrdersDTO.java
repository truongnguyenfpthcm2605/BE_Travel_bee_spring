package com.travelbee.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {
    private String qrcode;
    private String voucher;
    private String status;
    private String cccd;
    private String email;
    private String numberphone;
    private Double price;
    private Integer member;
    private String emailAccount;
    private Long planTourId;
}
