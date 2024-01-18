package com.travelbee.app.controller.client;

import com.travelbee.app.dto.response.Message;
import com.travelbee.app.enities.Access;
import com.travelbee.app.enities.Tour;
import com.travelbee.app.enities.Voucher;
import com.travelbee.app.exception.NotfoundException;
import com.travelbee.app.model.mail.MailerServiceImpl;
import com.travelbee.app.service.impl.AccessServiceImpl;
import com.travelbee.app.service.impl.TourServiceImpl;
import com.travelbee.app.service.impl.VoucherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/home")
@RequiredArgsConstructor
public class HomeController {

    private final TourServiceImpl tourService;
    private final VoucherServiceImpl voucherService;
    private final AccessServiceImpl accessService;

    @GetMapping("")
    public ResponseEntity<Message> home() {
        return new ResponseEntity<>(Message.builder().status("Hello").data("Ok").build(), HttpStatus.OK);
    }

    @GetMapping("/tourOutstanding")
    public ResponseEntity<Object> findTourOutstanding() {
        return new ResponseEntity<>(tourService.findTourOutstanding().subList(0, 6), HttpStatus.OK);
    }

    @GetMapping("/voucher/{id}")
    public ResponseEntity<Object> findVoucher(@PathVariable("id") String id) {
        Optional<Voucher> voucher = voucherService.findById(id);
        if(voucher.isPresent()){
            Voucher voucher1 = voucher.get();
            if(voucher1.getCondition() >= voucher1.getQuanity()){
                return new ResponseEntity<>(Message.builder().status("Voucher đã hết số lượng ! ").build(),HttpStatus.BAD_REQUEST);
            }else if(new Date().after(voucher1.getEnddate())){
                return new ResponseEntity<>(Message.builder().status("Voucher đã hết hạn !").build(),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(voucher1,HttpStatus.OK);

        }
        return new ResponseEntity<>(Message.builder().status("Voucher không tồn tại").build(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/voucher/update/{id}")
    public ResponseEntity<Object> updateVoucher(@PathVariable("id") String id) {
       Optional<Voucher> voucher = voucherService.findById(id);
       if(voucher.isPresent()){
           voucher.get().setCondition(voucher.get().getCondition()+1);
           return ResponseEntity.ok(voucherService.update(voucher.get()));
       }
       return ResponseEntity.badRequest().build();

    }

    @GetMapping("/update/access")
    public ResponseEntity<Object> updateAccess(){
        LocalDate date = LocalDate.now();
        Access access = accessService.findByAccessdate(date)
                .orElse(Access.builder().accessdate(date).accesscount(0L).build());
        access.setAccesscount(access.getAccesscount()+1);
        accessService.save(access);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/access/{date}")
    public ResponseEntity<Access> getAccess(@PathVariable("date") LocalDate date){
        return accessService.findByAccessdate(date).map(value -> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findall/access")
    public ResponseEntity<List<Access>> findAllAcess(){
        return new ResponseEntity<>(accessService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/notfound/{error}")
    public ResponseEntity<Object> notfound(@PathVariable("error") String error){
        if(error.equals("error")){
            return ResponseEntity.ok().build();
        }
        throw  new NotfoundException("Not found");
    }


}
