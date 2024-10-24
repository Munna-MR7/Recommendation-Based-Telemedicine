package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.service.ZegoCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/video")
public class VideoCallController {

    @Autowired
    private ZegoCloudService zegoCloudService;

    @GetMapping("/room") ///{doctorId}
    public String createRoomForDoctor(Model model){ //(@PathVariable Integer id, Model model) {
        String roomId = "doctor_" + 5;
        zegoCloudService.createRoom(roomId).subscribe();  // Asynchronous request
        model.addAttribute("roomId", roomId);
        model.addAttribute("doctorId", 5);

        return "videoCall";
    }

    @PostMapping("/join")
    public String joinCall(@RequestParam Integer id, @RequestParam String roomId, Model model) {
        String userId=Integer.toString(id);
        zegoCloudService.joinRoom(userId, roomId).subscribe();  // Asynchronous request
        model.addAttribute("roomId", roomId);
        model.addAttribute("userId", userId);
        return "redirect:/video/room/" + roomId;
    }

    @PostMapping("/leave")
    public String leaveCall(@RequestParam String userId, @RequestParam String roomId) {
        zegoCloudService.leaveRoom(userId, roomId).subscribe();  // Asynchronous request
        return "redirect:/video/room/" + roomId;
    }
}
