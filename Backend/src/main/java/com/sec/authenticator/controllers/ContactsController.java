package com.sec.authenticator.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactsController {

    @GetMapping("contact")
    public String getAccountDetails() {
        return "Inquiry details are saved to the DB";
    }

}
