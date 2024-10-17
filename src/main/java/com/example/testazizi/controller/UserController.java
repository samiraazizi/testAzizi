package com.example.testazizi.controller;

import com.example.testazizi.dto.CustomerRegistrationDTO;
import com.example.testazizi.entity.Customer;
import com.example.testazizi.entity.User;
import com.example.testazizi.service.AuthenticationService;
import com.example.testazizi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/customer")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<String>> getOrdersCustomer() {
        // one way to get the current Customer from session
        Customer currentCustomer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> list = new ArrayList<>();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/register")
    public Customer registerCustomer(@RequestBody CustomerRegistrationDTO customerRegistrationDTO) {
        return authenticationService.registerCustomer(customerRegistrationDTO);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
