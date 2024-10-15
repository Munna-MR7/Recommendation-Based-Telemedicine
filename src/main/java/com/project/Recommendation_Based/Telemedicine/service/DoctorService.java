package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.dto.UserDTO;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import java.util.Optional;


public interface DoctorService {
    List<Doctor> getDoctors();
    //public Doctor saveDoctor(Doctor doctor);

    Optional<Doctor> searchDoctor(Integer id);

    public void removeSessionMessage();

    void saveDoctorRequest(DoctorRequest doctorRequest);


    //void saveStudent(UserDTO user);

}
