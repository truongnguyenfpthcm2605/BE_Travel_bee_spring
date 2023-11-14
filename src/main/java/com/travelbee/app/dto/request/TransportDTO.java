package com.travelbee.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportDTO {
    private String title;
    private String phone;
    private String address;
    private String images;
    private String description;
    private String email;
}
