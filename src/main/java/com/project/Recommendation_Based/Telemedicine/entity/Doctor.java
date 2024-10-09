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
@Table(name = "Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String role;
    private String degrees;
    private String password;
    private String email;
    private String designation;
<<<<<<< HEAD
=======
    private String consultationFee;
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)



}