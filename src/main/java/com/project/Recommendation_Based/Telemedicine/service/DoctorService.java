package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.dto.UserDTO;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public interface DoctorService {
    List<Doctor> getDoctors();
    Optional<Doctor> searchDoctor(Integer id);

    public Doctor saveDoctor(Doctor doctor);
    public void removeSessionMessage();

    void saveStudent(UserDTO user);

//    Doctor saveDoctor(User user);
}
