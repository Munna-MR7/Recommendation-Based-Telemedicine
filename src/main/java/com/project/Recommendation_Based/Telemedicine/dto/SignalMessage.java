package com.project.Recommendation_Based.Telemedicine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignalMessage {
    private String type;
    private String sdp;
    private String candidate;

    // Getters and setters
}
