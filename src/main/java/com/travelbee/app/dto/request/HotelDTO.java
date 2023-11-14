package com.travelbee.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private String title;
    private String phone;
    private String address;
    private String description;
    private String images;
    private String email;
}
