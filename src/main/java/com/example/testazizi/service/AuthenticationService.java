package com.example.testazizi.service;

import com.example.testazizi.dto.CustomerRegistrationDTO;
import com.example.testazizi.entity.Customer;
import com.example.testazizi.entity.Role;
import com.example.testazizi.repository.CustomerRepository;
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
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer registerCustomer(CustomerRegistrationDTO customerRegistrationDTO) {
        String role = Boolean.TRUE.equals(customerRegistrationDTO.isUserAdmin) ? "ROLE_ADMIN" : "ROLE_USER";
        com.example.testazizi.entity.Role userRole = roleRepository.findByAuthority(role).orElseThrow(() -> new NoSuchElementException("Authority not present"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        Customer newCustomer = new Customer();
        newCustomer.setEmail(customerRegistrationDTO.email);
        newCustomer.setPassword(passwordEncoder.encode(customerRegistrationDTO.password));
        newCustomer.setAuthorities(authorities);
        newCustomer.setFirstname(customerRegistrationDTO.firstname);
        newCustomer.setSurname(customerRegistrationDTO.surname);
        newCustomer.setPhone(customerRegistrationDTO.phone);
        return customerRepository.save(newCustomer);
    }
}