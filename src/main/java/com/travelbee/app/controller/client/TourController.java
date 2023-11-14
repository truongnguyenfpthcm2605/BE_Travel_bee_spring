package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.TourDTO;
import com.travelbee.app.enities.Tour;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.TourServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tour")
public class TourController {

    private final AccountServiceImpl accountService;
    private final TourServiceImpl tourService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(tourService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<Tour> tour = tourService.findById(id);
        return tour.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody TourDTO tourDTO) {
        return new ResponseEntity<>(tourService.save(Tour.builder()
                .title(tourDTO.getTitle())
                .description(tourDTO.getDescription())
                .price(tourDTO.getPrice())
                .images(tourDTO.getImages())
                .views(1L)
                .isactive(true)
                .createdate(new Date())
                .updatedate(new Date())
                .account(accountService.findByEmail(tourDTO.getEmail()).get()).build()), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody TourDTO tourDTO, @PathVariable("id") Long id) {
        Optional<Tour> tour = tourService.findById(id);
        return tour.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(tourService.update(value.builder()
                .description(tourDTO.getDescription())
                .title(tourDTO.getTitle())
                .images(tourDTO.getImages())
                .price(tourDTO.getPrice())
                .updatedate(new Date())
                .account(accountService.findByEmail(tourDTO.getEmail()).get()).build()), HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        Optional<Tour> tour = tourService.findById(id);
        return tour.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(tourService.update(value.builder().isactive(false).build()), HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> updateView(@PathVariable("id") Long id) {
        Optional<Tour> tour = tourService.findById(id);
        return tour.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(tourService.update(value.builder().views(value.getViews() + 1L).build()), HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
