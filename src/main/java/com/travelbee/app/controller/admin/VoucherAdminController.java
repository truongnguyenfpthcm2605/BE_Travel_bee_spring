package com.travelbee.app.controller.admin;

import com.travelbee.app.dto.request.VoucherDTO;
import com.travelbee.app.enities.Account;
import com.travelbee.app.enities.Voucher;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.VoucherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class VoucherAdminController {

    private final VoucherServiceImpl voucherService;
    private final AccountServiceImpl accountService;

    @GetMapping("/staff/voucher/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(voucherService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/staff/voucher/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") String id) {
        return voucherService.findById(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/staff/voucher/save")
    public ResponseEntity<Object> save(@RequestBody VoucherDTO voucherDTO) {
        if(voucherService.findById(voucherDTO.getId()).isPresent()){
           return ResponseEntity.badRequest().build();
        }
        return accountService.findByEmail(voucherDTO.getEmail()).<ResponseEntity<Object>>map(value ->
                new ResponseEntity<>(voucherService.save(Voucher.builder()
                        .id(voucherDTO.getId())
                        .title(voucherDTO.getTitle())
                        .image(voucherDTO.getImage())
                        .condition(voucherDTO.getCondition())
                        .quanity(voucherDTO.getQuanity())
                        .discount(voucherDTO.getDiscount())
                        .createdate(voucherDTO.getCreatedate())
                        .enddate(voucherDTO.getEnddate())
                        .updatedate(new Date())
                        .isactive(true)
                        .account(value).build()), HttpStatus.OK)
        ).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @PutMapping("/staff/voucher/update/{id}")
    public ResponseEntity<Object> update(@RequestBody VoucherDTO voucherDTO, @PathVariable("id") String id) {
        Optional<Voucher> voucher = voucherService.findById(id);
        if(voucher.isPresent()){
            Voucher voucher1 = voucher.get();
            voucher1.setTitle(voucherDTO.getTitle());
            voucher1.setDiscount(voucherDTO.getDiscount());
            voucher1.setCondition(voucherDTO.getCondition());
            voucher1.setCreatedate(voucherDTO.getCreatedate());
            voucher1.setEnddate(voucherDTO.getEnddate());
            voucher1.setImage(voucherDTO.getImage());
            voucher1.setAccount(accountService.findByEmail(voucherDTO.getEmail()).get());
            return new ResponseEntity<>(voucherService.update(voucher1),HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/staff/voucher/active/{id}")
    public ResponseEntity<Object> active(@PathVariable("id") String id, @RequestParam("email") String email) {
        Optional<Voucher> voucher = voucherService.findById(id);
        Optional<Account> account = accountService.findByEmail(email);
        if (voucher.isPresent()) {
            voucher.get().setIsactive(false);
            account.ifPresent(value -> voucher.get().setAccount(value));
            voucherService.update(voucher.get());
        } else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/staff/voucher/find")
    public ResponseEntity<Object> findByIdOrTitle(@RequestParam("id") String id, @RequestParam("title") String title) {
        return new ResponseEntity<>(voucherService.findByIdOrTitle(id, title), HttpStatus.OK);
    }

    @GetMapping("/staff/voucher/active")
    public ResponseEntity<Object> findActive(@RequestParam("active") Boolean active) {
        return new ResponseEntity<>(voucherService.findByActive(active), HttpStatus.OK);
    }

    @DeleteMapping("/admin/voucher/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id){
      voucherService.deleteById(id);
      return ResponseEntity.ok().build();
    }


}
