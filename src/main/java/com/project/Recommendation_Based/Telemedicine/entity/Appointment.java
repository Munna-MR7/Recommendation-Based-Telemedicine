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
@Table(name = "Appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String patientName;
    private String email;
    private String phoneNumber;
    private String age;

    private String gender;
    private String address;
    private String MedicalReport;
    private String doctorName;
    private String appointmentDate;
    private String appointmentTime;
    private String consultationFee;



}