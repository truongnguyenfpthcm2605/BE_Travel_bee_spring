package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.TransportDTO;
import com.travelbee.app.enities.Transport;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.TransportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transport")
public class TransportController {
    private final TransportServiceImpl transportService;
    private final AccountServiceImpl accountService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(transportService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<Transport> transport = transportService.findById(id);
        return transport.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody TransportDTO transportDTO) {
        return new ResponseEntity<>(transportService.save(Transport.builder()
                .title(transportDTO.getTitle())
                .phone(transportDTO.getPhone())
                .address(transportDTO.getAddress())
                .images(transportDTO.getImages())
                .createdate(new Date())
                .updatedate(new Date())
                .description(transportDTO.getDescription())
                .isactive(true)
                .account(accountService.findByEmail(transportDTO.getEmail()).get()).build()), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody TransportDTO transportDTO, @PathVariable("id") Long id) {
        Optional<Transport> transport = transportService.findById(id);
        return transport.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(
                transportService.update(value.builder()
                        .title(transportDTO.getTitle())
                        .phone(transportDTO.getPhone())
                        .address(transportDTO.getAddress())
                        .images(transportDTO.getImages())
                        .updatedate(new Date())
                        .description(transportDTO.getDescription())
                        .isactive(true)
                        .account(accountService.findByEmail(transportDTO.getEmail()).get()).build()), HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        Optional<Transport> transport = transportService.findById(id);
        return transport.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(transportService.update(value.builder().isactive(false).build()), HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
