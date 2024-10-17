package com.example.testazizi.controller;

import com.example.testazizi.dto.UserRegistrationDTO;
import com.example.testazizi.entity.User;
import com.example.testazizi.service.AuthenticationService;
import com.example.testazizi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/customer")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<String>> getOrdersCustomer() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> list = new ArrayList<>();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/register")
    public User registerCustomer(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return authenticationService.registerCustomer(userRegistrationDTO);
    }
}
