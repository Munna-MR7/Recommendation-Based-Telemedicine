package com.project.Recommendation_Based.Telemedicine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/Profile")
    public String userProfile(){
        return "userProfile";
    }


}
