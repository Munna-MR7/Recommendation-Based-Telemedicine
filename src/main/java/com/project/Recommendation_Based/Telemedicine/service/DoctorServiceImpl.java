package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.dto.UserDTO;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRepo;

import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepo.findAll();
    }


    public Optional<Doctor> searchDoctor(Integer id) {
        return doctorRepo.findById(id);
    }


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Doctor saveDoctor(Doctor doctor) {

        String password=passwordEncoder.encode(doctor.getPassword());
        doctor.setPassword(password);
        doctor.setRole("ROLE_USER");
        return doctorRepo.save(doctor);
    }

    @Override
    public void removeSessionMessage() {

        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }

}
