package com.travelbee.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDTO {
    private String title;
    private String description;
    private Double price;
    private String images;
    private String email;
}
