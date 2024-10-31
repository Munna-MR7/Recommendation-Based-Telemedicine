package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.PatientRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Patient savePatient(Patient patient) {

        String password=passwordEncoder.encode(patient.getPassword());
        //System.out.println("Encoded password Patient: " + patient.getPassword());
        patient.setPassword(password);

        return patientRepo.save(patient);

    }



    @Override
    public void removeSessionMessage() {

        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }

    @Override
    public Patient getPatientProfile(String email) {
        return patientRepo.findByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }




}
