package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



public interface DoctorService {
    List<Doctor> getDoctors();
    public Doctor saveDoctor(Doctor doctor);

    Doctor saveDoctor(User user);
}
