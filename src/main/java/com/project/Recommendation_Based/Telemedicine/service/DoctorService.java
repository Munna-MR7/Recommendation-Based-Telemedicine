package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;
import com.project.Recommendation_Based.Telemedicine.entity.User;

import java.util.List;

import java.util.Optional;


public interface DoctorService {
    List<Doctor> getDoctors();
    Optional<Doctor> searchDoctor(Integer id);

    public void removeSessionMessage();

    void saveDoctorRequest(DoctorRequest doctorRequest);

    void saveDoctor(Doctor doctor);



    //void saveStudent(UserDTO user);

}
