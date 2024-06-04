package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.FeedBackDTO;
import com.travelbee.app.dto.response.Message;
import com.travelbee.app.enities.Account;
import com.travelbee.app.enities.Feedback;
import com.travelbee.app.model.mail.MailerServiceImpl;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.FeedbackServiceImpl;
import com.travelbee.app.util.Common;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final MailerServiceImpl mailerService;
    private final AccountServiceImpl accountService;
    private final FeedbackServiceImpl feedbackService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(feedbackService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") long id) {
        return feedbackService.findById(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("send-feedback")
    public ResponseEntity<Message> sendFeedback(@RequestBody FeedBackDTO feedBack) {
        Optional<Account> account = accountService.findByEmail(feedBack.getEmail());
        if (account.isPresent()) {
            try {
                // 1 Send mail -> admin
                String body = "<div class=\"container\" style=\"width: 80%; margin: 0 auto;\">\n" +
                        "        <h1 style=\"color: orangered; text-align: center;\">Phản Hồi Người Dùng</h1>\n" +
                        "        <hr>\n" +
                        "        <h3>Tên Người Dùng : " + account.get().getFullname() + "</h3>\n" +
                        "        <h3>Email :" + account.get().getEmail() + " </h3>\n" +
                        "        <h3>Ngày Phản Hồi : " + Common.dateFormat(new Date()) + "</h3>\n" +
                        "        <p style=\"font-style: italic; font-weight: 400;\">" + feedBack.getContent() + "</p>\n" +
                        "     <img src=\""+feedBack.getImages()+"\" style=\"width: 300px;\" alt=\"\">"+
                        "    </div>";
                mailerService.send(Common.EMAIL_ADMIN, Common.TITLE_SEND_FEEDBACK, body);
                Feedback feedback = new Feedback();
                feedback.setImages(feedBack.getImages());
                feedback.setContent(feedBack.getContent());
                feedback.setIsactive(false);
                feedback.setTitle(feedBack.getTitle());
                feedback.setCreatedate(new Date());
                feedback.setAccount(account.get());
                feedbackService.save(feedback);
                return new ResponseEntity<>(Message.builder().status("Gửi mail thành công !").build(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(Message.builder().status("Gửi mail thất bại !").build(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(Message.builder().status("Account không tồn tại !").build(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reply")
    public ResponseEntity<Object> reply(@RequestParam("id") Long id, @RequestParam("content") String content) {
        Optional<Feedback> feedback = feedbackService.findById(id);
        if (feedback.isPresent()) {
            try {
                Feedback fb = feedback.get();
                String body = " <div class=\"container\" style=\"width: 80%; margin: 0 auto;\">" +
                        "    <h1 style=\"color: #F29727; text-align: center;\">" + Common.TITLE_SEND_FEEDBACK + "</h1>\n" +
                        "    <hr>\n" +
                        "    <h3>Tên Người Dùng :" + fb.getAccount().getFullname() + "</h3>\n" +
                        "    <h3>Email :  " + fb.getAccount().getEmail() + "</h3>\n" +
                        "    <h3>Ngày Phản Hồi : " +
                        Common.dateFormat(fb.getCreatedate()) + "</h3>\n" +
                        "    <p style=\"font-style: italic; font-weight: 400;\">" + fb.getContent() + "</p>\n" +
                        "     <img src=\""+fb.getImages()+"\" style=\"width: 300px;\" alt=\"\">"+
                        "    <hr>\n" +
                        "    <h2 style=\"color: #606C5D; font-style: italic;\">" + Common.TITLE_REPLY_ADMIN + "</h2>\n" +
                        "    <p style=\"font-style: italic; font-weight: 400;\">" + content + "</p>\n" +
                        "    + \"<h3>Ngày Trả Lời : " + Common.dateFormat(new Date()) + "</h3>\n" +
                        "  </div>";
                mailerService.send(fb.getAccount().getEmail(), Common.TITLE_REPLY_ADMIN, body);
                fb.setIsactive(true);
                return new ResponseEntity<>(feedbackService.update(fb), HttpStatus.OK);

            } catch (MessagingException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        feedbackService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
