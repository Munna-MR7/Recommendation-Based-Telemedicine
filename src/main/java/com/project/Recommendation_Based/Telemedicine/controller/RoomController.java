package com.project.Recommendation_Based.Telemedicine.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/room")
public class RoomController {

    @GetMapping("/home")
    public String home() {
        return "redirect:/" + UUID.randomUUID().toString();
    }

    @GetMapping("/{roomId}")
    public String joinRoom(@PathVariable String roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "index";
    }
}
