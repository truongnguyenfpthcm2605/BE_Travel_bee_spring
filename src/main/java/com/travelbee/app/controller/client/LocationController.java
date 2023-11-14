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
        Optional<Location> location = locationService.findById(id);
        if (location.isPresent()) {
            return new ResponseEntity<>(location.get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody LocationDTO locationDTO) {
        return new ResponseEntity<>(locationService.save(new Location().builder()
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
        if (location.isPresent()) {
            return new ResponseEntity<>(locationService.save(location.get().builder()
                    .title(locationDTO.getTitle())
                    .address(locationDTO.getAddress())
                    .description(locationDTO.getDescription())
                    .images(locationDTO.getImages())
                    .latitude(locationDTO.getLatitude())
                    .longitude(locationDTO.getLongitude())
                    .updatedate(new Date())
                    .account(accountService.findByEmail(locationDTO.getEmail()).get()).build()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        Optional<Location> location = locationService.findById(id);
        if (location.isPresent()) {
            return new ResponseEntity<>(locationService.save(location.get().builder().isactive(false).build()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

}
