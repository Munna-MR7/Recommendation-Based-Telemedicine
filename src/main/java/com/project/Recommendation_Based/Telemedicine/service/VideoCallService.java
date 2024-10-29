package com.project.Recommendation_Based.Telemedicine.service;
import com.project.Recommendation_Based.Telemedicine.dto.SignalMessage;
import org.springframework.stereotype.Service;

@Service
public class VideoCallService {
    public void handleOffer(String roomId, SignalMessage offer) {
        // Handle room-specific logic for offer
    }

    public void handleAnswer(String roomId, SignalMessage answer) {
        // Handle room-specific logic for answer
    }
}
