package com.travelbee.app.controller.client;

import com.travelbee.app.dto.request.Login;
import com.travelbee.app.dto.request.Register;
import com.travelbee.app.dto.response.AccountResponse;
import com.travelbee.app.dto.response.Message;
import com.travelbee.app.enities.Account;
import com.travelbee.app.enities.Role;
import com.travelbee.app.model.mail.MailerServiceImpl;
import com.travelbee.app.security.jwt.JwtProvider;
import com.travelbee.app.security.userprincal.UserPrinciple;
import com.travelbee.app.service.impl.AccountServiceImpl;
import com.travelbee.app.service.impl.RoleServiceImpl;
import com.travelbee.app.util.Common;
import com.travelbee.app.util.Provider;
import com.travelbee.app.util.Randoms;
import com.travelbee.app.util.Roles;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthencationController {
    private static final String CODE_MAIL = Randoms.randomCodeMail();
    private final AccountServiceImpl accountService;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final MailerServiceImpl mailerService;


    @PostMapping("/register")
    public ResponseEntity<Message> register(@RequestBody Register register) {
        // check in exists system
        if (accountService.findByUsername(register.getUsername()).isPresent()) {
            return new ResponseEntity<>(Message.builder().status("Username đã tồn tai").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (accountService.findByEmail(register.getEmail()).isPresent()) {
            return new ResponseEntity<>(Message.builder().status("email đã tồn tai").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // save in database
        Set<Role> roles = getRoles(register.getRoles());
        Account account = Account.builder()
                .username(register.getUsername())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .birthday(register.getBirthday())
                .updatedate(new Date())
                .providerID(Provider.local.name())
                .createdate(new Date())
                .fullname(register.getFullname())
                .roles(roles)
                .isactive(true).build();
        accountService.save(account);

        // return token and register success
        String token = jwtProvider.createToken(new UserPrinciple(account));
        return new ResponseEntity<>(Message.builder().status("Đăng ký thành công").data(token).build(), HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        try {
            // auth system
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // return token and login success
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            String token = jwtProvider.createToken(userPrinciple);
            return new ResponseEntity<>(
                    AccountResponse.builder()
                            .fullName(userPrinciple.getFullname())
                            .authorities(userPrinciple.getAuthorities())
                            .email(userPrinciple.getUsername())
                            .token(token).build(), HttpStatus.OK);
        } catch (AuthenticationException exception) {
            return new ResponseEntity<>(Message.builder().status("Đăng nhập thất bại").build(), HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/oauth2")
    public ResponseEntity<Object> getAccountOAuth2() {
        Optional<Account> account = accountService.findByUsernameAndProviderID(
                Common.email_OAuth2,
                Common.providerId);
        UserPrinciple userPrinciple = new UserPrinciple(account.orElseThrow(() -> new UsernameNotFoundException("User not found")));
        String token = jwtProvider.createToken(userPrinciple);
        return account.<ResponseEntity<Object>>map(value ->
                        new ResponseEntity<>(AccountResponse.builder().fullName(userPrinciple.getFullname()).email(userPrinciple.getUsername()).token(token)
                                .authorities(userPrinciple.getAuthorities()).build(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(Message.builder().status("Login Fail").build(), HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/logout/success")
    public ResponseEntity<Object> logout() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fail")
    public ResponseEntity<Object> oauth2Fail() {
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/gmail")
    public ResponseEntity<Message> getMail(@RequestParam("email") String mail) {
        try {
            mailerService.send(mail, "Mail xác thực tài khoản từ Travel Bee",
                    "  <div style=width:80%; margin:0 auto;text-align: center ;>\n" +
                            "    <h1 style=color:#080202 ;>TraVel Bee</h1>\n" +
                            "    <p>Dùng mã này để xác minh địa chỉ email của bạn trên Travel Bee </p>\n" +
                            "    <p>Xin chào Bạn,Chúng tôi cần xác minh địa chỉ email của bạn để đảm bảo là có thể liên hệ với bạn sau khi xem xét\n" +
                            "      ID.</p>\n" +
                            "    <p>Chúng tôi cần xác minh địa chỉ email của bạn để đảm bảo là có thể liên hệ với bạn sau khi xem xét ID.</p>\n" +
                            "    <h5>Mã xác nhận</h5>" +
                            "<h2 style=color: #116D6E;>" + CODE_MAIL + "</h2>" +
                            "      <br>" +
                            "    <p style=font-size: 15px;font-weight: 200;>Tin nhắn này được gửi tới bạn theo yêu cầu của Travel Bee.\n" +
                            "      Travel Bee © 2023 All rights re6served. Privacy Policy|T&C|System Status</p>\n" +
                            "  </div>");
        } catch (MessagingException e) {
            return new ResponseEntity<>(Message.builder().status("Gủi mail thất bại!").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(Message.builder().status("Gủi mail thành công!").data(CODE_MAIL).build(), HttpStatus.OK);
    }

    @GetMapping("/denied")
    public ResponseEntity<Message> accessDenied() {
        return new ResponseEntity<>(Message.builder().status("Không có quyền truy cập").build(), HttpStatus.FORBIDDEN);
    }


    //check role and get roles
    private Set<Role> getRoles(String role) {
        Set<Role> roles = new HashSet<>();
        switch (role) {
            case "ADMIN" -> {
                Role roleAdmin = roleService.findByName(Roles.ADMIN).orElseThrow(() -> new RuntimeException("Role Not found"));
                roles.add(roleAdmin);
            }
            case "STAFF" -> {
                Role rolePM = roleService.findByName(Roles.STAFF).orElseThrow(() -> new RuntimeException("Role Not found"));
                roles.add(rolePM);
            }
            default -> {
                Role roleUser = roleService.findByName(Roles.USER).orElseThrow(() -> new RuntimeException("Role Not found"));
                roles.add(roleUser);
            }
        };
        return roles;
    }


}
