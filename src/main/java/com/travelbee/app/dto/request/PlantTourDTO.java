package com.travelbee.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantTourDTO {
    private Date startDate;
    private Date endDate;
    private String description;
    private String email;
    private Long tourId;

}
