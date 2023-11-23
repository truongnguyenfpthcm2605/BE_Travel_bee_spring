package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.CommentDTO;
import com.travelbee.app.enities.Comment;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.CommentServiceImpl;
import com.travelbee.app.service.impl.TourServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentController {
    private final CommentServiceImpl commentService;
    private final AccountServiceImpl accountService;
    private final TourServiceImpl tourService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        return commentService.findById(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.save(Comment.builder()
                .content(commentDTO.getContent())
                .image(commentDTO.getImage())
                .createdate(new Date())
                .isactive(true)
                .account(accountService.findByEmail(commentDTO.getEmail()).get())
                .tour(tourService.findById(commentDTO.getTourId()).get()).build()), HttpStatus.OK
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody CommentDTO commentDTO, @PathVariable("id") Long id) {
        Optional<Comment> comment = commentService.findById(id);
        return comment.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(
                commentService.update(Comment.builder()
                        .id(value.getId())
                        .content(commentDTO.getContent())
                        .image(commentDTO.getImage())
                        .createdate(new Date())
                        .isactive(true)
                        .account(accountService.findByEmail(commentDTO.getEmail()).get())
                        .tour(tourService.findById(commentDTO.getTourId()).get()).build())
                , HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if(comment.isPresent()){
            comment.get().setIsactive(false);
            return new ResponseEntity<>(commentService.update(comment.get()),HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
}
