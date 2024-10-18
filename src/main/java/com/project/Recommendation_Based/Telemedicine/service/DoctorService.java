package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;

import java.util.List;

import java.util.Optional;


public interface DoctorService {
    List<Doctor> getDoctors();
    public Doctor searchDoctor(Integer id);

    public void removeSessionMessage();

    //void saveDoctorRequest(DoctorRequest doctorRequest);

    void saveDoctor(Doctor doctor);

    Doctor getDoctorByEmail(String email);



    //void saveStudent(UserDTO user);

}
