package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.LocationDTO;
import com.travelbee.app.enities.Location;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.LocationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/location")
public class LocationController {
    private final LocationServiceImpl locationService;
    private final AccountServiceImpl accountService;
    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        return locationService.findById(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody LocationDTO locationDTO) {
        return new ResponseEntity<>(locationService.save(Location.builder()
                .title(locationDTO.getTitle())
                .address(locationDTO.getAddress())
                .description(locationDTO.getDescription())
                .images(locationDTO.getImages())
                .latitude(locationDTO.getLatitude())
                .longitude(locationDTO.getLongitude())
                .createdate(new Date())
                .updatedate(new Date())
                .isactive(true)
                .account(accountService.findByEmail(locationDTO.getEmail()).get()).build()), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody LocationDTO locationDTO, @PathVariable("id") Long id) {
        Optional<Location> location = locationService.findById(id);
        return location.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(locationService.save(Location.builder()
                        .isactive(value.getIsactive())
                        .id(value.getId())
                        .title(locationDTO.getTitle())
                        .address(locationDTO.getAddress())
                        .description(locationDTO.getDescription())
                        .images(locationDTO.getImages())
                        .latitude(locationDTO.getLatitude())
                        .longitude(locationDTO.getLongitude())
                        .updatedate(new Date())
                        .createdate(value.getCreatedate())
                        .account(accountService.findByEmail(locationDTO.getEmail()).get()).build()), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        Optional<Location> location = locationService.findById(id);
        if (location.isPresent()) {
            location.get().setIsactive(false);
            return new ResponseEntity<>(locationService.update(location.get()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

}
