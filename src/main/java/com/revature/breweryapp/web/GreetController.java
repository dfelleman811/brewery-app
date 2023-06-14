package com.revature.breweryapp.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GreetController {

    @GetMapping(produces="text/plain")
    public String greet() {
        return "Hello!";
    }
}
