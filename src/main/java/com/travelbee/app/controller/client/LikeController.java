package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.LikeDTO;
import com.travelbee.app.enities.Account;
import com.travelbee.app.enities.Likes;
import com.travelbee.app.enities.Tour;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.LikeServiceImpl;
import com.travelbee.app.service.impl.TourServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/like")
@RequiredArgsConstructor
public class LikeController {

    private final TourServiceImpl tourService;
    private final AccountServiceImpl accountService;
    private final LikeServiceImpl likeService;

    @GetMapping("/all/{id}")
    public ResponseEntity<Object> findAllLike(@PathVariable("id") Long id) {
        return new ResponseEntity<>(likeService.findAllLike(id), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody LikeDTO likeDTO) {
        Optional<Account> account = accountService.findByEmail(likeDTO.getEmail());
        if (account.isPresent()) {
            Likes likes = likeService.findByAccount(account.get().getId());
            if (Objects.nonNull(likes)) {
                likes.setIsactive(!likes.getIsactive());
                likeService.save(likes);
            } else {
                Optional<Tour> tour = tourService.findById(likeDTO.getTourId());
                likeService.save(Likes.builder()
                        .createdate(new Date())
                        .account(account.get())
                        .tour(tour.get())
                        .isactive(true).build());
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }
}
