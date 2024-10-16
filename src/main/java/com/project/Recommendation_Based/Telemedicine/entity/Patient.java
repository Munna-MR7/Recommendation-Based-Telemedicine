package com.project.Recommendation_Based.Telemedicine.entity;
import java.util.List;
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
@Table(name = "patients")
public class Patient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private String email;
        private String password;
        private String phoneNumber;
        private String dateOfBirth;
        private String address;
        private String profilePicturePath;

        @OneToMany(mappedBy = "patient")
        private List<Appointment> appointments;

        @OneToMany(mappedBy = "patient")
        private List<HealthRecord> healthRecords;

        @OneToMany(mappedBy = "patient")
        private List<Prescription> prescriptions;

        @OneToMany(mappedBy = "patient")
        private List<Payment> payments;


        @ManyToMany
        @JoinTable(
                name = "patient_doctor",
                joinColumns = @JoinColumn(name = "patient_id"),
                inverseJoinColumns = @JoinColumn(name = "doctor_id")
        )
        private List<Doctor> assignedDoctors;


        private String role;

}
