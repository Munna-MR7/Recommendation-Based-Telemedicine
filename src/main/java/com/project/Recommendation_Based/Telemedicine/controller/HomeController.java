package com.project.Recommendation_Based.Telemedicine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class HomeController {
    @RequestMapping("/")
    public String hello(){
        return "program Start successfully";
    }
}
