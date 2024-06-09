package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.PlantTourDTO;
import com.travelbee.app.enities.PlanTour;
import com.travelbee.app.service.PlanTourService;
import com.travelbee.app.service.TourService;
import com.travelbee.app.service.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/v1/plant-tour")
@RequiredArgsConstructor
public class PlantTourController {

    private final PlanTourService planTourService;
    private final AccountServiceImpl accountService;
    private final TourService tourService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody PlantTourDTO plantTourDTO){
        PlanTour planTour = new PlanTour();
        planTour.setCreatedate(new Date());
        planTour.setUpdatedate(new Date());
        planTour.setStardate(plantTourDTO.getStartDate());
        planTour.setEnddate(plantTourDTO.getEndDate());
        planTour.setTour(tourService.findById(plantTourDTO.getTourId()).get());
        planTour.setAccount(accountService.findByEmail(plantTourDTO.getEmail()).get());
        return ResponseEntity.ok(planTourService.save(planTour));

    }
}
