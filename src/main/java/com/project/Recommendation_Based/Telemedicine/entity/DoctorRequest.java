package com.project.Recommendation_Based.Telemedicine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DoctorRequestsTable")
public class DoctorRequest {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private String email;
        private String phone;
        private String password;
        private String gender;
        private String address;
        private String degrees;
        private String Specialist;
        private String consultationFee;
        private String documents;
        private String registrationDate;
}
