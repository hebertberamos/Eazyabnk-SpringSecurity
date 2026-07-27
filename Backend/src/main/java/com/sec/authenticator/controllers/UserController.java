package com.sec.authenticator.controllers;

import com.sec.authenticator.models.Customer;
import com.sec.authenticator.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository repository;
    private final PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody Customer customer) {
        try {
            String encryptedPassword = encoder.encode(customer.getPassword());
            customer.setPassword(encryptedPassword);

            Customer savedEntity = repository.save(customer);

            if(savedEntity.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("User registration failed");
            }

        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Exception occurred: " + ex);
        }

    }

}
