package com.project.Recommendation_Based.Telemedicine.controller;
import com.project.Recommendation_Based.Telemedicine.dto.SignalMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VideoCallController {

    @MessageMapping("/join/{roomId}")
    @SendTo("/topic/join/{roomId}")
    public SignalMessage joinRoom(SignalMessage message, @PathVariable String roomId) {
        return message; // Notify others of new join
    }

    @MessageMapping("/offer/{roomId}")
    @SendTo("/topic/offer/{roomId}")
    public SignalMessage sendOffer(SignalMessage message, @PathVariable String roomId) {
        return message;
    }

    @MessageMapping("/answer/{roomId}")
    @SendTo("/topic/answer/{roomId}")
    public SignalMessage sendAnswer(SignalMessage message, @PathVariable String roomId) {
        return message;
    }

    @MessageMapping("/ice/{roomId}")
    @SendTo("/topic/ice/{roomId}")
    public SignalMessage sendIceCandidate(SignalMessage message, @PathVariable String roomId) {
        return message;
    }
}
