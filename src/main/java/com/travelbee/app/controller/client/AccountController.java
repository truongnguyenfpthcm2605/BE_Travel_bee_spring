package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.AccountDTO;
import com.travelbee.app.enities.Account;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.util.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;

    @GetMapping("/{email}")
    public ResponseEntity<Object> getAccount(@PathVariable("email") String email) {
        Optional<Account> account = accountService.findByEmail(email);
        return account.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody AccountDTO accountDTO) {
        Optional<Account> account = accountService.findByEmail(accountDTO.getEmail());
       if(account.isPresent()){
           Account account1 = account.get();
           account1.setUpdatedate(new Date());
           account1.setBirthday(accountDTO.getBirthday());
           account1.setImage(accountDTO.getImage());
           account1.setFullname(accountDTO.getFullname());
           return new ResponseEntity<>(accountService.save(account1),HttpStatus.OK);
       }
       return ResponseEntity.badRequest().build();
    }
}
