package com.project.Recommendation_Based.Telemedicine.dto;
import lombok.Data;

@Data
public class SurveyDTO {
    // Question 1
    private String pain;
    private String painDetails;

    // Question 2
    private String fatigue;

    // Question 3
    private String nausea;
    private String nauseaFrequency;

    // Question 4
    private String dizziness;
    private String dizzinessFrequency;

    // Question 5
    private String cough;
    private String coughDuration;

    // Question 6
    private String fever;
    private String feverTemperature;

    // Question 7
    private String shortnessOfBreath;
    private String shortnessOfBreathFrequency;

    // Question 8
    private String digestiveIssues;
    private String digestiveIssuesDetails;

    // Question 9
    private String skinChanges;
    private String skinChangesDetails;

    // Question 10
    private String moodRating;
}
