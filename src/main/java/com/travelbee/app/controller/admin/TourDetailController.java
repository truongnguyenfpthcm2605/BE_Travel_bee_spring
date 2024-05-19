package com.travelbee.app.controller.admin;

import com.travelbee.app.dto.request.TourDetailDTO;
import com.travelbee.app.enities.Tourdetails;
import com.travelbee.app.service.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/staff")
@RequiredArgsConstructor
public class TourDetailController {
    private final HotelServiceImpl hotelService;
    private final TransportServiceImpl transportService;
    private final TourServiceImpl tourService;
    private final TourDetailsServiceImpl tourDetailsService;
    private final LocationServiceImpl locationService;

    @PostMapping("/tour-detail/save")
    public ResponseEntity<Object> save(@RequestBody TourDetailDTO tourDetailDTO) {
        Tourdetails tourdetails = new Tourdetails();
        tourdetails.setDescription(tourDetailDTO.getDescription());
        tourdetails.setTour(tourService.findById(tourDetailDTO.getTour()).get());
        tourdetails.setLocation(locationService.findById(tourDetailDTO.getLocation()).get());
        tourdetails.setTransport(transportService.findById(tourDetailDTO.getTransport()).get());
        tourdetails.setHotel(hotelService.findById(tourDetailDTO.getHotel()).get());
        return new ResponseEntity<>(tourDetailsService.save(tourdetails), HttpStatus.OK);
    }
}
