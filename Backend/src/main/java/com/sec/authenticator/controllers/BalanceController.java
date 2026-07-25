package com.sec.authenticator.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("myBalance")
    public String getAccountDetails() {
        return "Here is the balance details from the DB";
    }

}
