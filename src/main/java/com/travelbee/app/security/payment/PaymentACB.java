package com.travelbee.app.security.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentACB {
    private Integer ID;
    private Double AMOUNT;
    private String TYPE;
    private String DESCRIPTION;
    private String CURRENCY;
    private String DATE;

}
