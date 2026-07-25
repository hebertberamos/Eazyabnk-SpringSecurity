package com.sec.authenticator.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accessible")
public class AccessibleController {

    @GetMapping
    public String accessible() {
        return "Every one have access for this request";
    }

}
