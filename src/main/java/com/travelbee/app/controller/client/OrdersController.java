package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.OrdersDTO;
import com.travelbee.app.enities.Orders;
import com.travelbee.app.model.mail.MailerServiceImpl;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.OrdersServiceImpl;
import com.travelbee.app.service.impl.PlanTourServiceImpl;
import com.travelbee.app.util.Common;
import com.travelbee.app.util.QRcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersServiceImpl ordersService;
    private final AccountServiceImpl accountService;
    private final PlanTourServiceImpl planTourService;
    private final QRcodeService qRcodeService;
    private final MailerServiceImpl mailerService;
    private static final String QR_CODE_TICKET = "http://localhost:8080/checkTikect";


    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getTicketInTour(@PathVariable("id") Long id){
        return new ResponseEntity<>(ordersService.getTicketOnTour(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(ordersService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveTicket(@RequestBody OrdersDTO ordersDTO) {
        Orders orders = ordersService.save(
                Orders.builder()
                        .voucher(ordersDTO.getVoucher())
                        .qrcode(qRcodeService.generationQRcode(ordersDTO.getQrcode()))
                        .status(ordersDTO.getStatus())
                        .cccd(ordersDTO.getCccd())
                        .email(ordersDTO.getEmail())
                        .numberphone(ordersDTO.getNumberphone())
                        .price(ordersDTO.getPrice())
                        .member(ordersDTO.getMember())
                        .createdate(new Date())
                        .isactive(true)
                        .account(accountService.findByEmail(ordersDTO.getEmailAccount()).get())
                        .plantour(planTourService.findById(ordersDTO.getPlanTourId()).get()).build());
        sendmailSuccess(orders);
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        return ordersService.findById(id).<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable("id") Long id) {
        Optional<Orders> orders = ordersService.findById(id);
        if (orders.isPresent()) {
            orders.get().setIsactive(false);
            sendMailFailer(orders.get());
            return new ResponseEntity<>(ordersService.update(orders.get()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/history/{email}")
    public ResponseEntity<Object> findAllHistoryTicket(@PathVariable("email") String email) {
        return new ResponseEntity<>(ordersService.findticket(email), HttpStatus.OK);
    }

    private void sendmailSuccess(Orders orders) {
        String body = "<div style=\"text-align: center; width: 80%; margin: 0 auto;\">\n" +
                "        <h1 style=\"color: green;\">" + orders.getPlantour().getTour().getTitle() + "</h1>\n" +
                "        <img src=\"" + orders.getPlantour().getTour().getImages().split(",")[4] + "\" alt=\"\" style=\"width: 40%; margin:  0 auto; border-radius: 20px;\">\n" +
                "        <p>" + orders.getPlantour().getTour().getDescription() + "</p>\n" +
                "        <h1 style=\"color: orangered;\">Thông Tin Vé Tour</h1>\n" +
                "        <table style=\"width: 100%; border: 2px double orangered;\">\n" +
                "            \n" +
                "            <thead>\n" +
                "                <tr>\n" +
                "                    <th>Thông Tin</th>\n" +
                "                    <th>Chi Tiết</th>\n" +
                "                    \n" +
                "                </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td>Họ và tên</td>\n" +
                "                    <td>" + orders.getAccount().getFullname() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Căn cước công dân </td>\n" +
                "                    <td>" + orders.getCccd() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Email</td>\n" +
                "                    <td>" + orders.getEmail() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Điên thoại </td>\n" +
                "                    <td>" + orders.getNumberphone() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Ngày mua </td>\n" +
                "                    <td>" + Common.dateFormat(orders.getCreatedate()) + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Số người </td>\n" +
                "                    <td>" + orders.getMember() + " Người</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Giá tiền </td>\n" +
                "                    <td>" + Common.decimalFormat(orders.getPrice()) + " VND</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Ngày đi </td>\n" +
                "                    <td>" + Common.dateFormat(orders.getPlantour().getStardate()) + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Ngày về</td>\n" +
                "                    <td>" + Common.dateFormat(orders.getPlantour().getEnddate()) + "</td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "        <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/QR_code_for_mobile_English_Wikipedia.svg/1200px-QR_code_for_mobile_English_Wikipedia.svg.png\"  style=\"text-align: center; width: 200px; margin: 0 auto;\" alt=\"\"> <br>\n" +
                "        <a href=\"http://127.0.0.1:5501/index.html#!/tourdetail/" + orders.getPlantour().getTour().getId() +"\">Vào Website để xem thông tin chi tiết</a>\n" +
                "        <p style=\"font-style: italic; color: goldenrod;\">Chúc quý khách có một chuyến đi vui vẻ !</p>\n" +
                "    </div>";
        try {
            mailerService.send(orders.getAccount().getEmail(), "Xác Nhận Mua Vé Tour Của Khác Hàng", body);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void sendMailFailer(Orders orders){
        String body = "<div style=\"text-align: center; width: 80%; margin: 0 auto;\">\n" +
                " <h1 style=\"color: red;\">Xác Nhận Hủy Vé</h1>" +
                "        <h1 style=\"color: green;\">" + orders.getPlantour().getTour().getTitle() + "</h1>\n" +
                "        <img src=\"" + orders.getPlantour().getTour().getImages().split(",")[4] + "\" alt=\"\" style=\"width: 40%; margin:  0 auto; border-radius: 20px;\">\n" +
                "        <p>" + orders.getPlantour().getTour().getDescription() + "</p>\n" +
                "        <h1 style=\"color: orangered;\">Thông Tin Vé Tour</h1>\n" +
                "        <table style=\"width: 100%; border: 2px double orangered;\">\n" +
                "            \n" +
                "            <thead>\n" +
                "                <tr>\n" +
                "                    <th>Thông Tin</th>\n" +
                "                    <th>Chi Tiết</th>\n" +
                "                    \n" +
                "                </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td>Họ và tên</td>\n" +
                "                    <td>" + orders.getAccount().getFullname() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Căn cước công dân </td>\n" +
                "                    <td>" + orders.getCccd() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Email</td>\n" +
                "                    <td>" + orders.getEmail() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Điên thoại </td>\n" +
                "                    <td>" + orders.getNumberphone() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Ngày mua </td>\n" +
                "                    <td>" + Common.dateFormat(orders.getCreatedate()) + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Số người </td>\n" +
                "                    <td>" + orders.getMember() + " Người</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Giá tiền </td>\n" +
                "                    <td>" + Common.decimalFormat(orders.getPrice()) + " VND</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Ngày đi </td>\n" +
                "                    <td>" + Common.dateFormat(orders.getPlantour().getStardate()) + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>Ngày về</td>\n" +
                "                    <td>" + Common.dateFormat(orders.getPlantour().getEnddate()) + "</td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                " <p style=\"color: red;font-size: 30px;\">Số tiền "+Common.decimalFormat(orders.getPrice())+" VNĐ sẽ được liên hệ và chuyển lợi cho bạn trong thời gian sớm nhất !</p>" +
                "    </div>";
        try {
            mailerService.send(orders.getAccount().getEmail(), "Xác Nhận Hủy Vé", body);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
