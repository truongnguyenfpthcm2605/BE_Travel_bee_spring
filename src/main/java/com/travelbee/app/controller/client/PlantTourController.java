package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.PlantTourDTO;
import com.travelbee.app.enities.PlanTour;
import com.travelbee.app.exception.NotfoundException;
import com.travelbee.app.service.PlanTourService;
import com.travelbee.app.service.TourService;
import com.travelbee.app.service.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        planTour.setDescription(plantTourDTO.getDescription());
        planTour.setTour(tourService.findById(plantTourDTO.getTourId()+1).get());
        planTour.setAccount(accountService.findByEmail(plantTourDTO.getEmail()).get());
        return ResponseEntity.ok(planTourService.save(planTour));

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody PlantTourDTO plantTourDTO){
        PlanTour planTour = planTourService.findById(id).orElseThrow(() ->  new NotfoundException("Not found plant tour"));
        planTour.setStardate(plantTourDTO.getStartDate());
        planTour.setEnddate(plantTourDTO.getEndDate());
        planTour.setDescription(plantTourDTO.getDescription());
        return ResponseEntity.ok(planTourService.update(planTour));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        return planTourService.findById(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
