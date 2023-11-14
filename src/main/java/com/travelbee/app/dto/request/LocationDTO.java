package com.travelbee.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private String title;
    private Double latitude;
    private Double longitude;
    private String images;
    private String description;
    private String address;
    private String email;
}
