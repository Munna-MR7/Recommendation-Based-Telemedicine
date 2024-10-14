package com.project.Recommendation_Based.Telemedicine.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User {

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

	@OneToMany(mappedBy = "user")
	private List<Appointment> appointments;

	@OneToMany(mappedBy = "user")
	private List<HealthRecord> healthRecords;

	@OneToMany(mappedBy = "user")
	private List<Prescription> prescriptions;

	@OneToMany(mappedBy = "user")
	private List<Payment> payments;


	@ManyToMany
	@JoinTable(
			name = "user_doctor",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "doctor_id")
	)
	private List<Doctor> assignedDoctors;


	private String role;



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
