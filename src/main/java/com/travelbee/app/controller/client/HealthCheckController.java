package com.travelbee.app.controller.client;

import com.travelbee.app.enities.Role;
import com.travelbee.app.service.RoleService;
import com.travelbee.app.service.impl.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class HealthCheckController {
    private final RoleService roleService;

    @GetMapping("/ap1/v1/health")
    public ResponseEntity<String> healthCheck() throws Exception {
        List<Role> list = roleService.findAll();
        String computer = InetAddress.getLocalHost().getHostName();
        return new ResponseEntity<>("Stronger Server : " + computer + list.get(0).getName(), HttpStatus.OK);
    }
}
