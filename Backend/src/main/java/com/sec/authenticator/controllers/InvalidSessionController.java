package com.sec.authenticator.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvalidSessionController {

    @GetMapping("invalidSession")
    public String getInvalidSession() {
        return "Invalid session!";
    }

}
