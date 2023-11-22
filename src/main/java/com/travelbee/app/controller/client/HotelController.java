package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.HotelDTO;
import com.travelbee.app.enities.Hotel;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.HotelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hotel")
public class HotelController {
    private final HotelServiceImpl hotelService;
    private final AccountServiceImpl accountService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(hotelService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<Hotel> hotel = hotelService.findById(id);
        return hotel.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody HotelDTO hotelDTO) {
        return new ResponseEntity<>(hotelService.save(Hotel.builder()
                .title(hotelDTO.getTitle())
                .description(hotelDTO.getDescription())
                .images(hotelDTO.getDescription())
                .address(hotelDTO.getAddress())
                .phone(hotelDTO.getPhone())
                .createdate(new Date())
                .isactive(true)
                .account(accountService.findByEmail(hotelDTO.getEmail()).get()).build()), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody HotelDTO hotelDTO, @PathVariable("id") Long id) {
        Optional<Hotel> hotel = hotelService.findById(id);
        return hotel.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(hotelService.save(Hotel.builder()
                .id(value.getId())
                .title(hotelDTO.getTitle())
                .description(hotelDTO.getDescription())
                .images(hotelDTO.getDescription())
                .address(hotelDTO.getAddress())
                .phone(hotelDTO.getPhone())
                .updatedate(new Date())
                .createdate(value.getCreatedate())
                .isactive(value.getIsactive())
                .account(accountService.findByEmail(hotelDTO.getEmail()).get()).build()), HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        Optional<Hotel> hotel = hotelService.findById(id);
        return hotel.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(hotelService.save(value.builder().isactive(false).build()), HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findByTitle(@RequestParam("keyword") String keyword) {
        return new ResponseEntity<>(hotelService.findByTitle(keyword), HttpStatus.OK);
    }


}
