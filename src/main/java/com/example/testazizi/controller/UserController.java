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

/**
 * @Author samira
 * Here we can create new endpoints and then authenticate users and grant access to them to use these endpoints
 */
//todo create new apis as you need

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public User registerCustomer(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return authenticationService.registerCustomer(userRegistrationDTO);
    }
}
