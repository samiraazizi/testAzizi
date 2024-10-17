package com.example.testazizi.service;

import com.example.testazizi.dto.UserRegistrationDTO;
import com.example.testazizi.entity.User;
import com.example.testazizi.entity.Role;
import com.example.testazizi.repository.UserRepository;
import com.example.testazizi.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerCustomer(UserRegistrationDTO userRegistrationDTO) {
        String role = Boolean.TRUE.equals(userRegistrationDTO.isUserAdmin()) ? "ROLE_ADMIN" : "ROLE_USER";
        Role userRole = roleRepository.findByAuthority(role).orElseThrow(() -> new NoSuchElementException("Authority not present"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User newUser = new User();
        newUser.setEmail(userRegistrationDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        newUser.setAuthorities(authorities);
        newUser.setFirstname(userRegistrationDTO.getFirstname());
        newUser.setSurname(userRegistrationDTO.getSurname());
        newUser.setPhone(userRegistrationDTO.getPhone());
        return userRepository.save(newUser);
    }
}