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
        return transportService.findById(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
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
        if(transport.isPresent()){
            Transport transport1 = transport.get();
            transport1.setTitle(transportDTO.getTitle());
            transport1.setPhone(transportDTO.getPhone());
            transport1.setAddress(transportDTO.getAddress());
            transport1.setUpdatedate(new Date());
            transport1.setDescription(transportDTO.getDescription());
            transport1.setImages(transportDTO.getImages());
            transport1.setAccount(accountService.findByEmail(transportDTO.getEmail()).get());
            return new ResponseEntity<>(transportService.update(transport1),HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        Optional<Transport> transport = transportService.findById(id);
       if(transport.isPresent()){
           transport.get().setIsactive(!transport.get().getIsactive());
           return new ResponseEntity<>(transportService.update(transport.get()),HttpStatus.OK);
       }
       return ResponseEntity.badRequest().build();
    }


}
