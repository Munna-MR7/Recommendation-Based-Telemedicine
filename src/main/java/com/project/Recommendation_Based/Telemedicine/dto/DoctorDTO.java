package com.project.Recommendation_Based.Telemedicine.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DoctorDTO {
    public String name;
    public String email;
    public String password;
}
