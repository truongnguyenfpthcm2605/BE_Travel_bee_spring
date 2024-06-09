package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.TourDTO;
import com.travelbee.app.enities.PlanTour;
import com.travelbee.app.enities.Tour;
import com.travelbee.app.service.AccountService;
import com.travelbee.app.service.PlanTourService;
import com.travelbee.app.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/tour")
public class TourController {

    private final AccountService accountService;
    private final TourService tourService;
    private final PlanTourService planTourService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(tourService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        return tourService.findById(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody TourDTO tourDTO) {
        return new ResponseEntity<>(tourService.save(Tour.builder()
                .title(tourDTO.getTitle())
                .description(tourDTO.getDescription())
                .price(tourDTO.getPrice())
                .images(tourDTO.getImages())
                .location(tourDTO.getLocation())
                .views(1L)
                .isactive(true)
                .createdate(new Date())
                .updatedate(new Date())
                .account(accountService.findByEmail(tourDTO.getEmail()).get()).build()), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody TourDTO tourDTO, @PathVariable("id") Long id) {
        Optional<Tour> tour = tourService.findById(id);
        return tour.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(tourService.update(Tour.builder()
                        .id(value.getId())
                        .views(value.getViews())
                        .description(tourDTO.getDescription())
                        .title(tourDTO.getTitle())
                        .images(tourDTO.getImages())
                        .price(tourDTO.getPrice())
                        .location(tourDTO.getLocation())
                        .updatedate(new Date())
                        .createdate(value.getCreatedate())
                        .isactive(value.getIsactive())
                        .account(accountService.findByEmail(tourDTO.getEmail()).get()).build()), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        Optional<Tour> tour = tourService.findById(id);
        if (tour.isPresent()) {
            tour.get().setIsactive(false);
            return new ResponseEntity<>(tourService.update(tour.get()), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> updateView(@PathVariable("id") Long id) {
        Optional<Tour> tour = tourService.findById(id);
        if (tour.isPresent()) {
            tour.get().setViews(tour.get().getViews() + 1);
            return new ResponseEntity<>(tourService.update(tour.get()), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/plantour/{id}")
    public ResponseEntity<Object> findByPlanTour(@PathVariable("id") Long id) {
        return planTourService.findByPlanTour(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/plantour/all")
    public ResponseEntity<List<PlanTour>> findAllPlanTours() {
        List<PlanTour> planTours = planTourService.findAll();
        if (planTours.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(planTours, HttpStatus.OK);
    }


}
